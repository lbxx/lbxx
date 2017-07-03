package com.cdhaixun.shop.service;


import com.cdhaixun.domain.Appointment;

import java.util.Date;
import java.util.List;

/**
 * Created by tangxinmao on 2017/7/2.
 */
public interface IAppointmentService {
    void save(Appointment appointment);

    List<Appointment> findByStartTimeAndTechnicianId(Date starttime, Date endtime, Integer technicianid);

    List<Appointment> findByUserId(Integer userid);
}
