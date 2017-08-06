package com.cdhaixun.shop.service;

import com.cdhaixun.domain.StorePotion;

import java.util.List;

public interface IStorePotionService {
    List<StorePotion> findByStoreId(Integer storeid);

    StorePotion findOneByStoreIdAndPotionId(Integer storeid, Integer potionid);
}
