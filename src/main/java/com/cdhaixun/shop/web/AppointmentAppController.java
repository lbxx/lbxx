package com.cdhaixun.shop.web;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.cdhaixun.common.appVo.Appointment;
import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.domain.*;
import com.cdhaixun.shop.service.*;
import com.cdhaixun.util.PrintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2017-07-01.
 */
@Controller
@RequestMapping("appointmentApp")
public class AppointmentAppController {
    @Autowired
    private IAppointmentService appointmentService;
    @Autowired
    private ITimeBucketService timeBucketService;
    @Autowired
    private IAppointmentDetailService appointmentDetailService;
    @Autowired
    private ITechnicianService technicianService;
    @Autowired
    private IStoreBusinessService storeBusinessService;
    @Autowired
    private ITechnicianBusinessService technicianBusinessService;
    @Autowired
    private IBusinessService businessService;
    @Autowired
    private IStoreService storeService;
    @Autowired
    private IBabyService babyService;
    @Autowired
    private IPotionCategoryService potionCategoryService;
    @Autowired
    private IStorePotionService storePotionService;
    @Autowired
    private IPotionService potionService;
    @Autowired
    private IUserService userService;
    @Value("#{configProperties['domain']}")
    private String domain;
    /*采用线程池提高性能*/
    private static final Executor EXECUTOR = new ThreadPoolExecutor(30, 100, 30,
            TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(10)
    );

    @RequestMapping(value = "addAppointment", method = RequestMethod.POST)
    @ResponseBody
    public Result addAppointment(@RequestBody Appointment appointment, HttpServletRequest httpServletRequest) throws InvocationTargetException, IllegalAccessException, ParseException, AlipayApiException {
        Result result = new Result();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取预约起始时间
        List<com.cdhaixun.domain.Appointment> appointmentList = appointmentService.findByStartTimeAndTechnicianId(appointment.getStarttime(), appointment.getEndtime(), appointment.getTechnicianid());
        Date starttime = appointment.getStarttime();
        for (com.cdhaixun.domain.Appointment appointmentTemp : appointmentList) {
            if (appointmentTemp.getEndtime().compareTo(starttime) > 0) {
                starttime = appointmentTemp.getEndtime();
            }
        }
        final com.cdhaixun.domain.Appointment appointment1Db = new com.cdhaixun.domain.Appointment();
        // StorePotion storePotion=   storePotionService.findOneByStoreIdAndPotionId(appointment.getStoreid(),appointment.getPotionid());
        Potion potion = null;
        if (appointment.getPotionid() != null) {
            potion = potionService.findById(appointment.getPotionid());
            appointment1Db.setTotalprice(potion.getPrice().multiply(new BigDecimal(appointment.getPotionamount())));
            appointment1Db.setPotionprice(appointment1Db.getTotalprice());
        } else {
            appointment1Db.setTotalprice(new BigDecimal(0));
        }
        appointment1Db.setCreatetime(appointment.getCreatetime());
        appointment1Db.setTechnicianid(appointment.getTechnicianid());
        appointment1Db.setStoreid(appointment.getStoreid());
        appointment1Db.setEndtime(appointment.getEndtime());
        appointment1Db.setStarttime(appointment.getStarttime());
        appointment1Db.setRemark(appointment.getRemark());
        appointment1Db.setUserid(appointment.getUserid());
        appointment1Db.setStarttime(starttime);
        appointment1Db.setCreatetime(new Date());
        appointmentService.save(appointment1Db);
        appointment1Db.setBusinessList(new ArrayList<Business>());
        appointment1Db.setBabyList(new ArrayList<Baby>());
        appointment1Db.setTechnician(technicianService.findById(appointment.getTechnicianid()));
        appointment1Db.setStore(storeService.findById(appointment.getStoreid()));
        for (com.cdhaixun.domain.Baby baby : appointment.getBabyList()) {
            appointment1Db.getBabyList().add(babyService.findById(baby.getId()));
        }
        for (Business business : appointment.getBusinessList()) {
            TechnicianBusiness technicianBusiness = technicianBusinessService.findByBusinessIdAndTechnicianId(business.getId(), appointment.getTechnicianid());
            appointment1Db.getBusinessList().add(businessService.findById(business.getId()));
            StoreBusiness storeBusiness = storeBusinessService.findOneByStoreIdAndBusinessId(appointment.getStoreid(), business.getId());
            for (com.cdhaixun.domain.Baby baby : appointment.getBabyList()) {
                AppointmentDetail appointmentDetail = new AppointmentDetail();
                appointmentDetail.setTechnicianid(appointment.getTechnicianid());
                appointmentDetail.setAppointmentid(appointment1Db.getId());
                appointmentDetail.setUserid(appointment.getUserid());
                appointmentDetail.setBabyid(baby.getId());
                appointmentDetail.setStarttime(starttime);
                appointmentDetail.setPrice(storeBusiness.getPrice());
                appointment1Db.getTotalprice().add(storeBusiness.getPrice());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(starttime);
                calendar.add(Calendar.MINUTE, technicianBusiness.getSpend());
                starttime = calendar.getTime();
                appointmentDetail.setEndtime(calendar.getTime());
                appointmentDetail.setBussinessid(technicianBusiness.getBusinessid());
                appointment1Db.setEndtime(appointmentDetail.getEndtime());
                appointmentDetailService.save(appointmentDetail);
            }
        }
        //打印预约信息
        User user = userService.findById(appointment.getUserid());
        String content;
        content = "<CB>订单号：" + appointment1Db.getId() + "</CB><BR>";
        content += "--------------------------------<BR>";
        content += "会员名称：" + user.getName() + "<BR>";
        content += "会员电话：" + user.getMobile() + "<BR>";
        content += "--------------------------------<BR>";
        content += "宝宝名称：" + appointment1Db.getBabyList().get(0).getName() + "  宝宝性别：" + (!appointment1Db.getBabyList().get(0).getGender() ? "男" : "女") + "<BR>";
        Date birthday = appointment1Db.getBabyList().get(0).getBirthday();
        long year = (System.currentTimeMillis() - birthday.getTime()) / (1000 * 60 * 60 * 24 * 365);
        long mi = (System.currentTimeMillis() - birthday.getTime()) % (1000 * 60 * 60 * 24 * 365) / (1000 * 60 * 60 * 24 * 30);
        content += "宝宝年龄：" + (year == 0 ? "" : year + "年") + (mi == 0 ? "" : mi + "个月") + "<BR>";
        content += "--------------------------------<BR>";
        content += "预约日期：" + new SimpleDateFormat("yyyy年MM月dd日").format(appointment1Db.getCreatetime()) + "<BR>";
        content += "预约时间段：" + appointment.getStarttime() + "--" + appointment.getEndtime() + "点<BR>";
        content += "预约技师：" + appointment1Db.getTechnician().getName() + "<BR>";
        content += "预约药水：" + potion.getName() + "<BR>";
        content += "备注：" + appointment.getRemark() + "<BR>";
        content += "支付金额：" + appointment1Db.getTotalprice() + "元<BR>";
        //调用打印接口
        final String finalContent = content;
        EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                //查看打印的printUtils  调用printOrder(String content,String sn)
                   PrintUtil.printOrder(finalContent,appointment1Db.getStore().getPrintersn());
            }
        });

        //appointmentService.save(appointment1Db);
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2017122901329322", "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKwGqvE4cW0om07zPonF9GvbSttK2GGLHoLw9SqVVPgZafDAS3dcidwY6hMaWqJqhs7x5DlusIRFxWbn71A0FJr++u8th6EA+vSwU3EIKlbxgpK6B8M8Q9zPcmjjaJFlUOmiKi4W6zlW5uoH3XaLFXFiuRrnE4lvxQ3Slk5g6NrNAgMBAAECgYBVJevBAHYsWNgfCQmgekpUhLQVvYNDLPBKUeiYTlhDgZjNoPD5wOU/+1kJYZcRI3dcwaB8yQw4PKzMFet/oHe9utYSayiNJwkl7e0z/l2XSOu/+/l87duekU4YDuJ54/YonJlRXb2uFsA1BPPL+nUNMSZNjyNMY0Q7fD8S5cjigQJBAPDTrGkdtEa5ErtaxI6ZFxFGJkvJxLK9Jl2H9RYfDkQGlyJ95vHszETtYqsO6XU5afVTgJh8RqsSa5E55iyvISECQQC23UwmEPt/CxUsdwtyigP7Q9OmGJylOM1W6bkztbOpJ2+vLILQPoAaB6QYVu/HBVRIieAfJ57EB61W5KIPNwgtAkAKHrsGB8uFlU1mNiBAZcqEXVBKqwXrBOvRzl7MOS3eSfCb8HJ+BBdpZhhZW90PogQD3ShwP/iwQ6vlhGtZwVpBAkBMQp/WJDDt+246G+9PNhWQ/OlQFGWHVdf0jgYpdXZWbdbaxAJN3DLKKDOb3u2iHyvWEIHCHGDOubDlUvXuyHGFAkBviYuRetDRoQw+p3OhMdOt04A6YTea0ICf5DAeXYb4Ot+fOofcRRWhZ/VoB7pQeJFmQoahH041KUoMzS95fdOl", "json", "utf-8", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB", "RSA2");
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
      /*  model.setBody("我是测试数据");*/
        model.setSubject("预约");
        model.setOutTradeNo(appointment1Db.getId().toString());
        model.setTimeoutExpress("30m");
        model.setTotalAmount(appointment1Db.getTotalprice().toString());
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("http://1548i94i39.iok.la/" + "pay/alipay_notify_url");
        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
        System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
        appointment1Db.setAlipayTradeAppPayInfo(response.getBody());
        List<com.cdhaixun.domain.Appointment> appointmentList1 = appointmentService.findByStartTimeAndTechnicianId(new Date(), appointment1Db.getEndtime(), appointment.getTechnicianid());
        appointment1Db.setAppointnumber(appointmentList1.size());
        result.setData(appointment1Db);
        result.setResult(true);
        return result;
    }

    @RequestMapping(value = "modifyAppointmentState", method = RequestMethod.POST)
    @ResponseBody
    public Result modifyAppointmentState(@RequestBody Appointment appointment) throws InvocationTargetException, IllegalAccessException, ParseException {
        Result result = new Result();
        com.cdhaixun.domain.Appointment appointment1Db = appointmentService.findById(appointment.getId());

        result.setData(appointment1Db);
        result.setResult(true);
        return result;
    }

    @RequestMapping(value = "listByUserId", method = RequestMethod.POST)
    @ResponseBody
    public Result listByUserId(@RequestBody Appointment appointment) {
        Result result = new Result();
        List<com.cdhaixun.domain.Appointment> appointmentList = appointmentService.findByUserId(appointment.getUserid());
        for (com.cdhaixun.domain.Appointment appointment1 : appointmentList) {
            if (appointment1.getEndtime().compareTo(new Date()) < 0) {
                appointment1.setState("已结束");
            } else {
                appointment1.setState("预约中");
            }
            appointment1.setTechnician(technicianService.findById(appointment.getTechnicianid()));
            appointment1.setStore(storeService.findById(appointment.getStoreid()));

            List<AppointmentDetail> appointmentDetailList = appointmentDetailService.findByAppointmentId(appointment1.getId());
            Map<Integer, Business> map = new HashMap();
            for (AppointmentDetail appointmentDetail : appointmentDetailList) {
                Business business = businessService.findById(appointmentDetail.getBussinessid());
                if (map.keySet().contains(business.getId())) {
                    map.get(business.getId()).setNumber(map.get(business.getId()).getNumber() + 1);
                } else {
                    business.setNumber(1);
                    map.put(business.getId(), business);
                }
                appointmentDetail.setBusiness(business);
                appointmentDetail.setBaby(babyService.findById(appointmentDetail.getBabyid()));
            }
            appointment1.setBusinessList(new ArrayList<Business>());
            for (Business business : map.values()) {
                appointment1.getBusinessList().add(business);
            }
        }
        result.setData(appointmentList);
        result.setResult(true);
        return result;
    }

    @RequestMapping(value = "appointmentDetail", method = RequestMethod.POST)
    @ResponseBody
    public Result appointmentDetail(@RequestBody Appointment appointment) throws InvocationTargetException, IllegalAccessException, ParseException {
        Result result = new Result();
        com.cdhaixun.domain.Appointment appointment1Db = appointmentService.findById(appointment.getId());
        List<AppointmentDetail> appointmentDetailList = appointmentDetailService.findByAppointmentId(appointment1Db.getId());
        Map<Integer, Business> map = new HashMap();
        for (AppointmentDetail appointmentDetail : appointmentDetailList) {
            Business business = businessService.findById(appointmentDetail.getBussinessid());
            if (map.keySet().contains(business.getId())) {
                map.get(business.getId()).setNumber(map.get(business.getId()).getNumber() + 1);
            } else {
                business.setNumber(1);
                map.put(business.getId(), business);
            }
            appointmentDetail.setBusiness(business);
            appointmentDetail.setBaby(babyService.findById(appointmentDetail.getBabyid()));
        }
        appointment1Db.setAppointmentDetail(appointmentDetailList.get(0));
        appointment1Db.setStore(storeService.findById(appointment1Db.getStoreid()));
        appointment1Db.setTechnician(technicianService.findById(appointment1Db.getTechnicianid()));
        result.setData(appointment1Db);
        result.setResult(true);
        return result;
    }



}
