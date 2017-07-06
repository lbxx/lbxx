package com.cdhaixun.persistence;

import com.cdhaixun.domain.Appointment;
import com.cdhaixun.vo.AppointmentVo;

import java.util.List;
import java.util.Map;

public interface AppointmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Appointment record);

    int insertSelective(Appointment record);

    Appointment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Appointment record);

    int updateByPrimaryKey(Appointment record);

    List<Appointment> selectByAppointment(Appointment appointment);

    List<AppointmentVo> getAppointmentList(Map<String, Object> parMap);
}