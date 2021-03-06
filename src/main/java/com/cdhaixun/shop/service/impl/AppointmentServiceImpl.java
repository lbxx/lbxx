package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.Appointment;
import com.cdhaixun.persistence.AppointmentMapper;
import com.cdhaixun.shop.service.IAppointmentService;
import com.cdhaixun.vo.AppointmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            appointmentMapper.insertSelective(appointment);
        }else {
            appointmentMapper.updateByPrimaryKeySelective(appointment);
        }
    }

    @Override
    public List<Appointment> findByStartTimeAndTechnicianId(Date starttime, Date endtime, Integer technicianid) {
        Appointment appointment=new Appointment();
        appointment.setTechnicianid(technicianid);
        appointment.setStarttimeFrom(starttime);
        appointment.setStarttimeTo(endtime);
        return appointmentMapper.selectByAppointment(appointment);
    }

    @Override
    public List<Appointment> findByUserId(Integer userId,String state) {
        Appointment appointment=new Appointment();
        appointment.setUserid(userId);
        if ("ALL".equals(state)) { //查询全部
            appointment.setState(null);
        }else{
            appointment.setState(state);
        }
        appointment.setOrderBy("createtime desc");
        return appointmentMapper.selectByAppointment(appointment);
    }

    @Override
    public List<AppointmentVo> getAppointmentList(Map<String, Object> parMap) {
        return appointmentMapper.getAppointmentList(parMap);
    }

    @Override
    public Appointment findById(Integer id) {
        return appointmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public Appointment findByOutTradeNo(String outTradeNo) {
        return appointmentMapper.findByOutTradeNo(outTradeNo);
    }

    @Override
    public Appointment findIdAndUserId(Integer id, Integer userid) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("userid", userid);
        return appointmentMapper.findByIdAndUserId(params);
    }

}
