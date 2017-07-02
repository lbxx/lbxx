package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.Appointment;
import com.cdhaixun.persistence.AppointmentMapper;
import com.cdhaixun.shop.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
