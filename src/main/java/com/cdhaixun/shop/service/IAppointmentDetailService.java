package com.cdhaixun.shop.service;

import com.cdhaixun.domain.AppointmentDetail;

import java.util.Date;
import java.util.List;

/**
 * Created by tangxinmao on 2017/7/2.
 */
public interface IAppointmentDetailService {

    List<AppointmentDetail> findByStartTimeAndTechnicianId(Date createtimeFrom, Date createtimeTo, Integer technicianid);

    void save(AppointmentDetail appointmentDetail);
}
