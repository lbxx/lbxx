package com.cdhaixun.shop.service;

import com.cdhaixun.common.service.BaseService;
import com.cdhaixun.domain.PotionCategory;
import com.cdhaixun.util.Pager;

import java.util.List;
import java.util.Map;

public interface IPotionCategoryService extends BaseService<PotionCategory>{


    @Override
    PotionCategory findById(Integer potioncategoryid);

    Pager getPotionCategoryList(Pager pager, Map<String, Object> parMap);

    List<PotionCategory> findAll();
}
