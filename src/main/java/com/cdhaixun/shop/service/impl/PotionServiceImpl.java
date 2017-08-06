package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.Potion;
import com.cdhaixun.persistence.PotionMapper;
import com.cdhaixun.shop.service.IPotionService;
import org.springframework.beans.factory.annotation.Autowired;

public class PotionServiceImpl implements IPotionService
{
    @Autowired
    private PotionMapper potionMapper;
    @Override
    public Potion findById(Integer potionid) {
        return potionMapper.selectByPrimaryKey(potionid);
    }
}
