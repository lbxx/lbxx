package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.Appointment;
import com.cdhaixun.domain.AppointmentDetail;
import com.cdhaixun.persistence.AppointmentMapper;
import com.cdhaixun.shop.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by tangxinmao on 2017/7/2.
 */
@Service
public class AppointmentServiceImpl implements IAppointmentService {
    @Autowired
    private AppointmentMapper appointmentMapper;
    @Override
    public void save(Appointment appointment) {
        if(appointment.getId()==null){
            appointmentMapper.insert(appointment);
        }else {
            appointmentMapper.updateByPrimaryKeySelective(appointment);
        }
    }

    @Override
    public List<Appointment> findByStartTimeAndTechnicianId(Date starttime, Date endtime, Integer technicianid) {
        Appointment appointment=new Appointment();
        appointment.setTechnicianid(technicianid);
        appointment.setStarttime(starttime);
        appointment.setEndtime(endtime);
        return appointmentMapper.selectByAppointment(appointment);
    }

    @Override
    public List<Appointment> findByUserId(Integer userid) {
        Appointment appointment=new Appointment();
        appointment.setTechnicianid(userid);
        return appointmentMapper.selectByAppointment(appointment);
    }
}
