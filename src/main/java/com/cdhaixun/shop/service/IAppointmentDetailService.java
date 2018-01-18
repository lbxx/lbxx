package com.cdhaixun.shop.service;

import com.cdhaixun.domain.Appointment;
import com.cdhaixun.domain.AppointmentDetail;
import com.cdhaixun.vo.AppointmentDetailVo;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by tangxinmao on 2017/7/2.
 */
public interface IAppointmentDetailService {

    List<AppointmentDetail> findByStartTimeAndTechnicianId(Date createtimeFrom, Date createtimeTo, Integer technicianid);

    void save(AppointmentDetail appointmentDetail);

    /**
     * 查询预约详情
     * @param parMap
     * @return
     */
    List<AppointmentDetailVo> getAppointmentDetailList(Map<String, Object> parMap);

    List<AppointmentDetail> findByAppointmentId(Integer id);

    Integer countQueueLength(Appointment appointment) throws IllegalAccessException, InvocationTargetException, IntrospectionException;
}
