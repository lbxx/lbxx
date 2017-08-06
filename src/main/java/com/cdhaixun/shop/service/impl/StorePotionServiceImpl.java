package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.StorePotion;
import com.cdhaixun.persistence.StorePotionMapper;
import com.cdhaixun.shop.service.IStorePotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorePotionServiceImpl implements IStorePotionService {
    @Autowired
    private StorePotionMapper storePotionMapper;

    @Override
    public StorePotion findOneByStoreIdAndPotionId(Integer storeid, Integer potionid) {
        StorePotion storePotion=new StorePotion();
        storePotion.setStoreid(storeid);
        storePotion.setPotionid(potionid);
        return storePotionMapper.selectOneByStorePotion(storePotion);
    }

    @Override
    public List<StorePotion> findByStoreId(Integer storeid) {
        StorePotion storePotion=new StorePotion();
        storePotion.setStoreid(storeid);

        return storePotionMapper.selectByStorePotion(storePotion);
    }
}
