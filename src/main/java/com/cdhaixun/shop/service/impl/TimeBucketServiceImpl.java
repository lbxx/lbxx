package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.TimeBucket;
import com.cdhaixun.persistence.TimeBucketMapper;
import com.cdhaixun.shop.service.ITimeBucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tangxinmao on 2017/7/2.
 */
@Service
public class TimeBucketServiceImpl implements ITimeBucketService {
@Autowired
private TimeBucketMapper timeBucketMapper;
    @Override
    public List<TimeBucket> findAll() {
        TimeBucket timeBucket=new TimeBucket();
        return timeBucketMapper.selectByTimeBucket(timeBucket);
    }
}
