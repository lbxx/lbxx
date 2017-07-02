package com.cdhaixun.persistence;

import com.cdhaixun.domain.Baby;

import java.util.List;

public interface BabyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Baby record);

    int insertSelective(Baby record);

    Baby selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Baby record);

    int updateByPrimaryKey(Baby record);

    List<Baby> selectByBaby(Baby baby);
}