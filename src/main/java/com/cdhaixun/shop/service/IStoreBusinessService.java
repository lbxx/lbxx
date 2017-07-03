package com.cdhaixun.shop.service;

import com.cdhaixun.domain.StoreBusiness;

import java.util.List;

/**
 * Created by tangxinmao on 2017/7/2.
 */
public interface IStoreBusinessService {
    /**
     * 根据bussinessid查询商家
     * @param id
     * @return
     */
    List<StoreBusiness> findByBusinessId(Integer id);

    List<StoreBusiness> findByStoreId(Integer storeid);
}
