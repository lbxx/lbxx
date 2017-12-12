package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.TechnicianBusiness;
import com.cdhaixun.persistence.TechnicianBusinessMapper;
import com.cdhaixun.shop.service.ITechnicianBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangxinmao on 2017/7/2.
 */
@Service
public class TechnicianBusinessServiceImpl implements ITechnicianBusinessService{
    @Autowired
    private TechnicianBusinessMapper technicianBusinessMapper;
    @Override
    public List<TechnicianBusiness> findByBusinessIdList(List<Integer> businessidList,Integer storeId) {
        Map<String,Object> params = new HashMap<>();
//        TechnicianBusiness technicianBusiness=new TechnicianBusiness();
//        technicianBusiness.setBusinessidList(businessidList);
//        technicianBusiness.setIsdelete(false);
        params.put("businessidList", businessidList);
        params.put("storeId", storeId);
        return technicianBusinessMapper.selectByTechnicianBusinessAndStoreId(params);
    }

    @Override
    public TechnicianBusiness findByBusinessIdAndTechnicianId(Integer id, Integer technicianid) {
        TechnicianBusiness technicianBusiness=new TechnicianBusiness();
        technicianBusiness.setBusinessid(id);
        technicianBusiness.setTechnicianid(technicianid);
        technicianBusiness.setIsdelete(false);
        return technicianBusinessMapper.selectOneByTechnicianBusiness(technicianBusiness);
    }

    @Override
    public List<TechnicianBusiness> findByTechnicianId(Integer technicianId) {
      return  technicianBusinessMapper.selectByTechnicianId(technicianId);
    }

    @Override
    public void deleteByTechnicianId(int technicianId) {
        technicianBusinessMapper.deleteByTechnicianId(technicianId);
    }
}
