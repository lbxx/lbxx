package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.TimeBucket;
import com.cdhaixun.persistence.TimeBucketMapper;
import com.cdhaixun.shop.service.ITimeBucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        TimeBucket timeBucket = new TimeBucket();
        return timeBucketMapper.selectByTimeBucket(timeBucket);
    }

    @Override
    public TimeBucket findOneByCreateTime(Date createtime) {
        List<TimeBucket> timeBucketList = findAll();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(createtime);
        calendar.set(Calendar.YEAR,0);
        calendar.set(Calendar.MONTH,0);
        calendar.set(Calendar.DATE,0);
        for (TimeBucket timeBucket : timeBucketList ) {
        if(calendar.getTime().compareTo(timeBucket.getStarttime())>=0&&calendar.getTime().compareTo(timeBucket.getEndtime())<0){
            return timeBucket;
        }
        }
        return null;
    }
}
