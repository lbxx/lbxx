package com.cdhaixun.persistence;

import com.cdhaixun.domain.PotionCategory;

import java.util.List;
import java.util.Map;

public interface PotionCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PotionCategory record);

    PotionCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(PotionCategory record);

    List<PotionCategory> findPotionCategoryList(Map<String, Object> parMap);

    List<PotionCategory> selectPotionCategoryAllList();
}