package com.cdhaixun.persistence;

import com.cdhaixun.domain.PotionCategory;

public interface PotionCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PotionCategory record);

    int insertSelective(PotionCategory record);

    PotionCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PotionCategory record);

    int updateByPrimaryKey(PotionCategory record);
}