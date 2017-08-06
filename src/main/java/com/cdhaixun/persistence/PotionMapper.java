package com.cdhaixun.persistence;

import com.cdhaixun.domain.Potion;

public interface PotionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Potion record);

    int insertSelective(Potion record);

    Potion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Potion record);

    int updateByPrimaryKey(Potion record);
}