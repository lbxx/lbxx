package com.cdhaixun.shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdhaixun.domain.ChainStore;
import com.cdhaixun.domain.Store;
import com.cdhaixun.persistence.ChainStoreMapper;
import com.cdhaixun.persistence.StoreMapper;
import com.cdhaixun.shop.service.IStoreService;
import com.cdhaixun.util.Pager;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class StoreServiceImpl implements IStoreService {
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private ChainStoreMapper chainStoreMapper;
    @Override
    public List<Store> listAllStores() {
        return storeMapper.listAllStores();
    }
    public List<ChainStore> listChainStores(){
        return chainStoreMapper.listChainStores();
    }
    @Override
    public Pager getStoreList(Pager pager, String name) {
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("name", name);
        Page dbpage = PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<Store> stores = storeMapper.listAllStores();
        pager.setTotal(dbpage.getTotal());
        pager.setResult(stores);
        pager.setPages(dbpage.getPages());
        return pager;
    }
    /*
     * 根据id删除store
     */
    @Override
    public int deleteStoreById(int id) {
        return storeMapper.deleteByPrimaryKey(id);
    }
    @Override
    public int updateByPrimaryKeySelective(Store store) {
        return storeMapper.updateByPrimaryKey(store);
    }
    @Override
    public List<Store> selectByPrimaryKey(int id) {
        return storeMapper.selectByPrimaryKey(id);        
    }
    
}
