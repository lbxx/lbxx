package com.cdhaixun.persistence;

import com.cdhaixun.domain.StorePotion;

public interface StorePotionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StorePotion record);

    int insertSelective(StorePotion record);

    StorePotion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StorePotion record);

    int updateByPrimaryKey(StorePotion record);
}