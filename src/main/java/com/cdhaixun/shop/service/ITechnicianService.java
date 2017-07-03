package com.cdhaixun.shop.service;

import java.util.List;
import java.util.Map;

import com.cdhaixun.common.service.BaseService;
import com.cdhaixun.common.vo.Result;
import com.cdhaixun.domain.Technician;
import com.cdhaixun.util.Pager;

public interface ITechnicianService extends BaseService<Technician>{
    Pager selectTechnicianList(Pager pager, Map<String, Object> parMap);

    List<Technician> findByStoreId(Integer storeid);

    Result save(Technician technician,Map<String, Object> parMap);
    Technician selectByPrimaryKey(Integer technicianId);
}
