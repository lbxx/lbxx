package com.cdhaixun.persistence;

import com.cdhaixun.domain.Appointment;

import java.util.List;

public interface AppointmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Appointment record);

    int insertSelective(Appointment record);

    Appointment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Appointment record);

    int updateByPrimaryKey(Appointment record);

    List<Appointment> selectByAppointment(Appointment appointment);
}