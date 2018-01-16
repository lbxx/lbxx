package com.cdhaixun.shop.service;


import com.cdhaixun.domain.Appointment;
import com.cdhaixun.vo.AppointmentVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by tangxinmao on 2017/7/2.
 */
public interface IAppointmentService {
    void save(Appointment appointment);

    List<Appointment> findByStartTimeAndTechnicianId(Date starttime, Date endtime, Integer technicianid);

    List<Appointment> findByUserId(Integer userid,String state);

    /**
     * 预约列表
     * @param parMap
     * @return
     */
    List<AppointmentVo> getAppointmentList(Map<String, Object> parMap);

    Appointment findById(Integer id);
}
