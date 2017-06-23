package com.cdhaixun.persistence;

import java.util.List;

import com.cdhaixun.domain.StoreBusiness;

public interface StoreBusinessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StoreBusiness record);

    int insertStoreBusinessSelective(StoreBusiness record);

    StoreBusiness selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreBusiness record);

    int updateByPrimaryKey(StoreBusiness record);
    List<StoreBusiness> getStoreBusinessByStoreId(int storeId);
}