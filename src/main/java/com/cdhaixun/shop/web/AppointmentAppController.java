package com.cdhaixun.shop.web;

import com.cdhaixun.common.appVo.Appointment;
import com.cdhaixun.common.appVo.Baby;
import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.domain.AppointmentDetail;
import com.cdhaixun.domain.Business;
import com.cdhaixun.domain.TechnicianBusiness;
import com.cdhaixun.domain.TimeBucket;
import com.cdhaixun.shop.service.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
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
    private ITechnicianBusinessService technicianBusinessService;

    @RequestMapping(value = "addAppointment", method = RequestMethod.POST)
    @ResponseBody
    public Result addAppointment(@RequestBody Appointment appointment, HttpServletRequest httpServletRequest) throws InvocationTargetException, IllegalAccessException {
        Result result = new Result();
        //获取预约起始时间
        List<AppointmentDetail> appointmentDetailList = appointmentDetailService.findByStartTimeAndTechnicianId(appointment.getStarttime(), appointment.getEndtime(), appointment.getTechnicianid());
        Date starttime = appointment.getStarttime();
        for (AppointmentDetail appointmentDetail : appointmentDetailList) {
            if (appointmentDetail.getEndtime().compareTo(starttime) > 0) {
                starttime = appointmentDetail.getEndtime();
            }
        }
        com.cdhaixun.domain.Appointment appointment1Db=new com.cdhaixun.domain.Appointment();
        BeanUtils.copyProperties(appointment1Db,appointment);
        appointmentService.save(appointment1Db);
        for (Business business : appointment.getBusinessList()) {
            TechnicianBusiness technicianBusiness=technicianBusinessService.findByBusinessIdAndTechnicianId(business.getId(),appointment.getTechnicianid());
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
                appointmentDetailService.save(appointmentDetail);
            }
        }
        result.setResult(true);
        return result;
    }


}
