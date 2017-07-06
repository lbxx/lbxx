package com.cdhaixun.persistence;

import com.cdhaixun.domain.AppointmentDetail;
import com.cdhaixun.vo.AppointmentDetailVo;

import java.util.List;
import java.util.Map;

public interface AppointmentDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppointmentDetail record);

    int insertSelective(AppointmentDetail record);

    AppointmentDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppointmentDetail record);

    int updateByPrimaryKey(AppointmentDetail record);

    List<AppointmentDetail> selectByAppointmentDetail(AppointmentDetail appointmentDetail);

    List<AppointmentDetailVo> getAppointmentDetailList(Map<String, Object> parMap);
}