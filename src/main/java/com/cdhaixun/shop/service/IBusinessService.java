package com.cdhaixun.shop.service;

import com.cdhaixun.domain.Business;

import java.util.List;

/**
 * Created by Administrator on 2017-07-01.
 */
public interface IBusinessService {

    List<Business> findByCategoryId(Integer id);

    Business findById(Integer businessid);
    List<Business> findByStoreId(Integer storeId);
}
