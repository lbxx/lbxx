package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.PotionCategory;
import com.cdhaixun.persistence.PotionCategoryMapper;
import com.cdhaixun.shop.service.IPotionCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PotionCategoryServiceImpl implements IPotionCategoryService {
    @Autowired
    private PotionCategoryMapper potionCategoryMapper;

    @Override
    public PotionCategory findById(Integer potioncategoryid) {
        return potionCategoryMapper.selectByPrimaryKey(potioncategoryid);
    }
}
