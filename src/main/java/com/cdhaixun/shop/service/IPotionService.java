package com.cdhaixun.shop.service;

import com.cdhaixun.common.service.BaseService;
import com.cdhaixun.domain.Potion;
import com.cdhaixun.domain.PotionCategory;
import com.cdhaixun.util.Pager;

import java.util.List;
import java.util.Map;

public interface IPotionService extends BaseService<Potion>{
    Potion findById(Integer potionid);

    Pager getPotionList(Pager pager, Map<String, Object> parMap);

    List<PotionCategory> selectPotionCategoryList();
}
