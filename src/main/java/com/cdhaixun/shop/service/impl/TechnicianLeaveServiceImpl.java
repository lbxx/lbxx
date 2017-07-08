package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.TechnicianLeave;
import com.cdhaixun.persistence.TechnicianLeaveMapper;
import com.cdhaixun.shop.service.ITechnicianLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by tangxinmao on 2017/7/2.
 */
@Service
public class TechnicianLeaveServiceImpl implements ITechnicianLeaveService {
    @Autowired
    private TechnicianLeaveMapper technicianLeaveMapper;
    @Override
    public TechnicianLeave findOneByLeaveDay(Date createtime, Integer technicianid) {
        TechnicianLeave technicianLeave=new TechnicianLeave();
        technicianLeave.setLeaveday(createtime);
        technicianLeave.setTechnicianid(technicianid);
        return technicianLeaveMapper.selectOneByTechnicianLeave(technicianLeave);
    }
}
