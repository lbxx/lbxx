package com.cdhaixun.persistence;

import com.cdhaixun.domain.AppointmentDetail;

import java.util.List;

public interface AppointmentDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppointmentDetail record);

    int insertSelective(AppointmentDetail record);

    AppointmentDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppointmentDetail record);

    int updateByPrimaryKey(AppointmentDetail record);

    List<AppointmentDetail> selectByAppointmentDetail(AppointmentDetail appointmentDetail);
}