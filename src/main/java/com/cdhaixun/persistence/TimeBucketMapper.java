package com.cdhaixun.persistence;

import com.cdhaixun.domain.TimeBucket;

public interface TimeBucketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TimeBucket record);

    int insertSelective(TimeBucket record);

    TimeBucket selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TimeBucket record);

    int updateByPrimaryKey(TimeBucket record);
}