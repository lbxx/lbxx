package com.cdhaixun.shop.web;

import com.cdhaixun.common.appVo.Appointment;
import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.domain.*;
import com.cdhaixun.shop.service.*;
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
    private IAppointmentService appointmentService;
    @Autowired
    private ITechnicianLeaveService technicianLeaveService;
    @Autowired
    private ITechnicianService technicianService;

    @ResponseBody
    @RequestMapping(value = "listByCondition", method = RequestMethod.POST)
    public Result listByCondition(@RequestBody Appointment appointment) {
        Result result = new Result();
        result.setResult(true);
        //判断员工是否请假
        List<TechnicianLeave> technicianLeaveList = technicianLeaveService.findOneByLeaveDay(appointment.getCreatetime(), appointment.getTechnicianid());

        //判断员工是否上班
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(appointment.getCreatetime());
        Technician technician = technicianService.findById(appointment.getTechnicianid());
        if (!technician.getWorkday().contains((calendar.get(Calendar.DAY_OF_WEEK) + 1) + "")) {
            return result;
        }
        List<TimeBucket> timeBucketList = timeBucketService.findAll();
        Iterator<TimeBucket> timeBucketIterator = timeBucketList.iterator();
        while (timeBucketIterator.hasNext()) {
            TimeBucket timeBucket = timeBucketIterator.next();
            //过滤请假时间
            for (TechnicianLeave technicianLeave : technicianLeaveList) {
                if (technicianLeave.getStarttime().compareTo(timeBucket.getStarttime()) <= 0
                        && technicianLeave.getEndtime().compareTo(timeBucket.getStarttime()) >= 0 ||
                        technicianLeave.getStarttime().compareTo(timeBucket.getEndtime()) <= 0
                                && technicianLeave.getEndtime().compareTo(timeBucket.getEndtime()) >= 0
                        ) {
                    continue;
                }
            }

            Date createtimeFrom = new Date(appointment.getCreatetime().getTime() + timeBucket.getStarttime().getTime());
            Date createtimeTo = new Date(appointment.getCreatetime().getTime() + timeBucket.getEndtime().getTime());
            List<com.cdhaixun.domain.Appointment> appointmentList = appointmentService.findByStartTimeAndTechnicianId(createtimeFrom, createtimeTo, appointment.getTechnicianid());
            if (CollectionUtils.isNotEmpty(appointmentList)) {
                for (com.cdhaixun.domain.Appointment appointmentTemp : appointmentList) {
                    if (appointmentTemp.getEndtime().compareTo(createtimeTo) >= 0) {
                        timeBucketIterator.remove();
                    }
                }
            }
        }
        result.setData(timeBucketList);

        return result;
    }
}
