package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.AppointmentDetail;
import com.cdhaixun.persistence.AppointmentDetailMapper;
import com.cdhaixun.shop.service.IAppointmentDetailService;
import com.cdhaixun.vo.AppointmentDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by tangxinmao on 2017/7/2.
 */
@Service
public class AppointmentDetailServiceImpl implements IAppointmentDetailService {
    @Autowired
    private AppointmentDetailMapper appointmentDetailMapper;
    @Override
    public List<AppointmentDetail> findByStartTimeAndTechnicianId(Date createtimeFrom, Date createtimeTo, Integer technicianid) {
        AppointmentDetail appointmentDetail=new AppointmentDetail();
        appointmentDetail.setTechnicianid(technicianid);
        appointmentDetail.setCreatetimeFrom(createtimeFrom);
        appointmentDetail.setCreatetimeTo(createtimeTo);
        return appointmentDetailMapper.selectByAppointmentDetail(appointmentDetail);
    }

    @Override
    public void save(AppointmentDetail appointmentDetail) {
        if(appointmentDetail.getId()==null){
            appointmentDetailMapper.insert(appointmentDetail);
        }else{
            appointmentDetailMapper.updateByPrimaryKeySelective(appointmentDetail);
        }
    }

    @Override
    public List<AppointmentDetailVo> getAppointmentDetailList(Map<String, Object> parMap) {
        return appointmentDetailMapper.getAppointmentDetailList(parMap);
    }
}
