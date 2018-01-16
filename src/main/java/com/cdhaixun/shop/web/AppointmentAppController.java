package com.cdhaixun.shop.web;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.cdhaixun.common.appVo.Appointment;
import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.common.emun.AppointmentState;
import com.cdhaixun.domain.AppointmentDetail;
import com.cdhaixun.domain.Baby;
import com.cdhaixun.domain.Business;
import com.cdhaixun.domain.Potion;
import com.cdhaixun.domain.StoreBusiness;
import com.cdhaixun.domain.TechnicianBusiness;
import com.cdhaixun.domain.User;
import com.cdhaixun.shop.service.*;
import com.cdhaixun.util.PrintUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Value("#{configProperties['appId']}")
    private String appId;
    @Value("#{configProperties['privateKey']}")
    private String privateKey;
    @Value("#{configProperties['format']}")
    private String format;
    @Value("#{configProperties['publicKey']}")
    private String publicKey;
    @Value("#{configProperties['signType']}")
    private String signType;
    @Value("#{configProperties['alipayDomain']}")
    private String alipayDomain;
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
        List<com.cdhaixun.domain.Appointment> appointmentList = appointmentService.findByStartTimeAndTechnicianId(appointment.getStarttime(), 
                appointment.getEndtime(), appointment.getTechnicianid());
        
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
        appointment1Db.setState(AppointmentState.NOPAY.toString());
        appointment1Db.setOutTradeNo(String.valueOf(System.currentTimeMillis()));
        //appointmentService.save(appointment1Db);
        AlipayClient alipayClient = new DefaultAlipayClient(alipayDomain,appId, privateKey, "json","utf-8",publicKey, signType);
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
      /*  model.setBody("我是测试数据");*/
        model.setSubject("预约");
        model.setOutTradeNo(appointment1Db.getOutTradeNo());
        model.setTimeoutExpress("30m");
//        model.setTotalAmount(appointment1Db.getTotalprice().toString());
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(domain + "pay/alipay_notify_url");
        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
        System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
        appointment1Db.setAlipayTradeAppPayInfo(response.getBody());
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
        List<com.cdhaixun.domain.Appointment> appointmentList = appointmentService.findByUserId(appointment.getUserid(),appointment.getState());
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
    @RequestMapping(value = "tradeCreate", method = RequestMethod.POST)
    @ApiOperation(value = "支付宝创建新订单", notes = "支付宝创建新订单", httpMethod = "POST")
    @ResponseBody
    public Result tradeCreate(@RequestBody com.cdhaixun.domain.Appointment appointment, HttpServletRequest httpServletRequest)
            throws AlipayApiException {

        AlipayClient alipayClient = new DefaultAlipayClient(alipayDomain, appId, privateKey, "json", "utf-8", publicKey,
                signType);
        // 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        // SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        /* model.setBody("我是测试数据"); */
        model.setSubject("预约");
        model.setOutTradeNo(appointment.getOutTradeNo().toString());
        model.setTimeoutExpress("30m");
        model.setTotalAmount(appointment.getTotalprice().toString());
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(domain + "pay/alipay_notify_url");
        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
        System.out.println(response.getBody());// 就是orderString
                                               // 可以直接给客户端请求，无需再做处理。
        appointment.setAlipayTradeAppPayInfo(response.getBody());
        appointmentService.save(appointment);
        Result result = new Result();
        result.setData(appointment);
        result.setResult(true);
        return result;
    }
    
    @RequestMapping(value = "cancelAppointment", method = RequestMethod.POST)
    @ApiOperation(value = "取消预约", notes = "取消预约", httpMethod = "POST")
    @ApiParam(value = "id")
    @ResponseBody
    public Result tradeClose(@ApiParam(name="id",value="预约id",required=true) @RequestParam int id,
            HttpServletRequest httpServletRequest)
            throws AlipayApiException {
        Result result = new Result<>();
        com.cdhaixun.domain.Appointment appointment = appointmentService.findById(id);
        //调用支付宝交易关闭接口
        AlipayClient alipayClient = new DefaultAlipayClient(alipayDomain,appId,privateKey,"json","utf-8",publicKey,signType);
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        request.setBizContent("{" +
        "\"out_trade_no\":\""+appointment.getOutTradeNo()+"\"" +
        "  }");
        AlipayTradeCloseResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
        System.out.println("调用成功");
        } else {
        System.out.println("调用失败");
        }
        try {
            appointment.setState(AppointmentState.CANCEL.toString());
            appointmentService.save(appointment);
            result.setResult(true);
            result.setMsg("预约取消成功");
            return result;
        } catch (Exception e) {
            result.setResult(false);
            result.setMsg("预约取消失败");
            e.printStackTrace();
            return result;
        }
    }


}
