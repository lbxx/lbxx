package com.cdhaixun.shop.service;

import java.util.List;
import java.util.Map;

import com.cdhaixun.common.vo.Result;
import com.cdhaixun.domain.Business;
import com.cdhaixun.util.Pager;

/**
 * Created by Administrator on 2017-07-01.
 */
public interface IBusinessService {

    List<Business> findByCategoryId(Integer id);

    Business findById(Integer businessid);
    List<Business> findByStoreId(Integer storeId);

    Pager selectBusinessList(Pager pager, Map<String, Object> parMap);

    Result save(Business business, Map<String, Object> parMap);

    void deleteByBusinessId(int businessId);

    Business getBusinessInfoByBusinessId(int businessId);
}
