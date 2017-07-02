package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.TechnicianBusiness;
import com.cdhaixun.persistence.TechnicianBusinessMapper;
import com.cdhaixun.shop.service.ITechnicianBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tangxinmao on 2017/7/2.
 */
@Service
public class TechnicianBusinessServiceImpl implements ITechnicianBusinessService{
    @Autowired
    private TechnicianBusinessMapper technicianBusinessMapper;
    @Override
    public List<TechnicianBusiness> findByBusinessIdList(List<Integer> businessidList) {
        TechnicianBusiness technicianBusiness=new TechnicianBusiness();
        technicianBusiness.setBusinessidList(businessidList);
        return technicianBusinessMapper.selectByTechnicianBusiness(technicianBusiness);
    }
}
