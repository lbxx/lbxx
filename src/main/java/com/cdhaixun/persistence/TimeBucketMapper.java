package com.cdhaixun.persistence;

import com.cdhaixun.domain.TimeBucket;

import java.util.List;

public interface TimeBucketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TimeBucket record);

    int insertSelective(TimeBucket record);

    TimeBucket selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TimeBucket record);

    int updateByPrimaryKey(TimeBucket record);

    List<TimeBucket> selectByTimeBucket(TimeBucket timeBucket);
}