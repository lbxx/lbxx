package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.StoreBusiness;
import com.cdhaixun.persistence.StoreBusinessMapper;
import com.cdhaixun.shop.service.IStoreBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tangxinmao on 2017/7/2.
 */
@Service
public class StoreBusinessServiceImpl implements IStoreBusinessService{

    @Autowired
    private StoreBusinessMapper storeBusinessMapper;

    @Override
    public List<StoreBusiness> findByBusinessId(Integer id) {
        StoreBusiness storeBusiness=new StoreBusiness();
        storeBusiness.setBusinessid(id);
        return storeBusinessMapper.selectByStoreBusiness(storeBusiness);
    }

    @Override
    public StoreBusiness findOneByStoreIdAndBusinessId(Integer storeid, Integer id) {

        StoreBusiness storeBusiness=new StoreBusiness();
        storeBusiness.setBusinessid(id);
        storeBusiness.setStoreid(storeid);
        return storeBusinessMapper.selectOneByStoreBusiness(storeBusiness);
    }

    @Override
    public List<StoreBusiness> findByStoreId(Integer storeid) {
        StoreBusiness storeBusiness=new StoreBusiness();
        storeBusiness.setStoreid(storeid);
        storeBusiness.setIsdelete(false);
        return storeBusinessMapper.selectByStoreBusiness(storeBusiness);
    }
}
