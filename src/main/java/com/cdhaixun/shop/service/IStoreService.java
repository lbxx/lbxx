package com.cdhaixun.shop.service;

import java.util.List;

import com.cdhaixun.domain.ChainStore;
import com.cdhaixun.domain.Store;
import com.cdhaixun.util.Pager;

public interface IStoreService {
    List<Store> listAllStores();
    List<ChainStore> listChainStores();
    Pager getStoreList(Pager pager, String name);
    int deleteStoreById(int id);
    int updateByPrimaryKeySelective(Store store);
    List<Store> selectByPrimaryKey(int id);
}
