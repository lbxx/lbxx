package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.Business;
import com.cdhaixun.persistence.BusinessMapper;
import com.cdhaixun.shop.service.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017-07-01.
 */
@Service
public class BusinessServiceImpl implements IBusinessService{
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public List<Business> findByCategoryId(Integer id) {
        Business business=new Business();
        business.setCategoryid(id);
        return businessMapper.selectByBusiness(business);
    }
}
