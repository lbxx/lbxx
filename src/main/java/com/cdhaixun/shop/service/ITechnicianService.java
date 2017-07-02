package com.cdhaixun.shop.service;

import java.util.List;
import java.util.Map;

import com.cdhaixun.common.service.BaseService;
import com.cdhaixun.domain.Technician;
import com.cdhaixun.domain.User;
import com.cdhaixun.domain.UserType;
import com.cdhaixun.util.Pager;

public interface ITechnicianService extends BaseService<Technician>{
    Pager selectTechnicianList(Pager pager, Map<String, Object> parMap);

    List<UserType> selectTypeList();

    void delete(User user);

    List<Technician> findByStoreId(Integer storeid);
}
