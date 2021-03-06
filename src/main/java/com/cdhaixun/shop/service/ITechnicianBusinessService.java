package com.cdhaixun.shop.service;

import com.cdhaixun.domain.TechnicianBusiness;

import java.util.List;

/**
 * Created by tangxinmao on 2017/7/2.
 */
public interface ITechnicianBusinessService {

    List<TechnicianBusiness> findByBusinessIdList(List<Integer> businessidList, Integer storeId);

    TechnicianBusiness findByBusinessIdAndTechnicianId(Integer id, Integer technicianid);
   List<TechnicianBusiness> findByTechnicianId(Integer technicianid);

void deleteByTechnicianId(int technicianId);
}
