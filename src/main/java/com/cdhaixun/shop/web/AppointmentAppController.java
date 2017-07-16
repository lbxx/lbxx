package com.cdhaixun.shop.web;

import com.cdhaixun.common.appVo.Appointment;
import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.domain.AppointmentDetail;
import com.cdhaixun.domain.Baby;
import com.cdhaixun.domain.Business;
import com.cdhaixun.domain.TechnicianBusiness;
import com.cdhaixun.shop.service.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    private ITechnicianBusinessService technicianBusinessService;
    @Autowired
    private IBusinessService businessService;
    @Autowired
    private IStoreService storeService;
    @Autowired
    private IBabyService babyService;

    @RequestMapping(value = "addAppointment", method = RequestMethod.POST)
    @ResponseBody
    public Result addAppointment(@RequestBody Appointment appointment, HttpServletRequest httpServletRequest) throws InvocationTargetException, IllegalAccessException, ParseException {
        Result result = new Result();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取预约起始时间
        List<com.cdhaixun.domain.Appointment> appointmentList = appointmentService.findByStartTimeAndTechnicianId(simpleDateFormat.parse(appointment.getStarttime()),simpleDateFormat.parse( appointment.getEndtime()), appointment.getTechnicianid());
        Date starttime = simpleDateFormat.parse(appointment.getStarttime());
        for (com.cdhaixun.domain.Appointment appointmentTemp : appointmentList) {
            if (appointmentTemp.getEndtime().compareTo(starttime) > 0) {
                starttime = appointmentTemp.getEndtime();
            }
        }
        com.cdhaixun.domain.Appointment appointment1Db=new com.cdhaixun.domain.Appointment();
        appointment1Db.setCreatetime(appointment.getCreatetime());
        appointment1Db.setTechnicianid(appointment.getTechnicianid());
        appointment1Db.setStoreid(appointment.getStoreid());
        appointment1Db.setEndtime(simpleDateFormat.parse(appointment.getEndtime()));
        appointment1Db.setStarttime(simpleDateFormat.parse(appointment.getStarttime()));
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
            TechnicianBusiness technicianBusiness=technicianBusinessService.findByBusinessIdAndTechnicianId(business.getId(),appointment.getTechnicianid());
            appointment1Db.getBusinessList().add(businessService.findById(business.getId()));
            for (com.cdhaixun.domain.Baby baby : appointment.getBabyList()) {
                AppointmentDetail appointmentDetail = new AppointmentDetail();
                appointmentDetail.setTechnicianid(appointment.getTechnicianid());
                appointmentDetail.setAppointmentid(appointment1Db.getId());
                appointmentDetail.setUserid(appointment.getUserid());
                appointmentDetail.setBabyid(baby.getId());
                appointmentDetail.setStarttime(starttime);
                Calendar calendar=Calendar.getInstance();
                calendar.setTime(starttime);
                calendar.roll(Calendar.MINUTE,technicianBusiness.getSpend());
                starttime=calendar.getTime();
                appointmentDetail.setEndtime(calendar.getTime());
                appointmentDetail.setBussinessid(technicianBusiness.getBusinessid());
                appointment1Db.setEndtime(appointmentDetail.getEndtime());
                appointmentDetailService.save(appointmentDetail);
            }
        }
        appointmentService.save(appointment1Db);
        List<com.cdhaixun.domain.Appointment> appointmentList1 = appointmentService.findByStartTimeAndTechnicianId(new Date(), appointment1Db.getEndtime(), appointment.getTechnicianid());
        appointment1Db.setAppointnumber(appointmentList1.size());


        result.setData(appointment1Db);
        result.setResult(true);
        return result;
    }

    @RequestMapping(value = "listByUserId", method = RequestMethod.POST)
    @ResponseBody
    public Result listByUserId(@RequestBody Appointment appointment){
        Result result = new Result();
       List<com.cdhaixun.domain.Appointment> appointmentList= appointmentService.findByUserId(appointment.getUserid());
        for (com.cdhaixun.domain.Appointment appointment1:appointmentList){
            appointment1.setTechnician(technicianService.findById(appointment.getTechnicianid()));
            appointment1.setStore(storeService.findById(appointment.getStoreid()));
            List<AppointmentDetail> appointmentDetailList=  appointmentDetailService.findByAppointmentId(appointment1.getId());
             for (AppointmentDetail appointmentDetail: appointmentDetailList){
                 appointmentDetail.setBusiness(businessService.findById(appointmentDetail.getBussinessid()));
                 appointmentDetail.setBaby(babyService.findById(appointmentDetail.getBabyid()));
            }
        }
        result.setData(appointmentList);
        result.setResult(true);
        return result;
    }
}
