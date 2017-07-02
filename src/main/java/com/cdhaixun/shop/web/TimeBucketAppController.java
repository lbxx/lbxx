package com.cdhaixun.shop.web;

import com.cdhaixun.common.appVo.Appointment;
import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.domain.AppointmentDetail;
import com.cdhaixun.domain.TechnicianBusiness;
import com.cdhaixun.domain.TechnicianLeave;
import com.cdhaixun.domain.TimeBucket;
import com.cdhaixun.shop.service.IAppointmentDetailService;
import com.cdhaixun.shop.service.ITechnicianLeaveService;
import com.cdhaixun.shop.service.ITechnicianService;
import com.cdhaixun.shop.service.ITimeBucketService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tangxinmao on 2017/7/2.
 */
@Controller
@RequestMapping("timeBucketApp")
public class TimeBucketAppController {
    @Autowired
    private ITimeBucketService timeBucketService;
    @Autowired
    private IAppointmentDetailService appointmentDetailService;
    @Autowired
    private ITechnicianLeaveService technicianLeaveService;

    @ResponseBody
    @RequestMapping(value = "listByCondition", method = RequestMethod.POST)
    public Result listByCondition(@RequestBody Appointment appointment) {
        Result result = new Result();
        result.setResult(true);
        //判断员工是否请假
        TechnicianLeave technicianLeave=technicianLeaveService.findOneByLeaveDay(appointment.getCreatetime());
        if(technicianLeave==null)
        return result;
        List<TimeBucket> timeBucketList = timeBucketService.findAll();
        Iterator<TimeBucket> timeBucketIterator = timeBucketList.iterator();
        while (timeBucketIterator.hasNext()) {
            TimeBucket timeBucket = timeBucketIterator.next();
            Date createtimeFrom = new Date(appointment.getCreatetime().getTime() + timeBucket.getStarttime().getTime());
            Date createtimeTo = new Date(appointment.getCreatetime().getTime() + timeBucket.getEndtime().getTime());
            List<AppointmentDetail> appointmentDetailList = appointmentDetailService.findByStartTimeAndTechnicianId(createtimeFrom, createtimeTo, appointment.getTechnicianid());
            if (CollectionUtils.isNotEmpty(appointmentDetailList)) {
                for (AppointmentDetail appointmentDetail : appointmentDetailList) {
            if(appointmentDetail.getEndtime().compareTo(createtimeTo)>=0){
                timeBucketIterator.remove();
            }
                }
            }
        }
        result.setData(timeBucketList);

        return result;
    }
}
