package com.cdhaixun.persistence;

import java.util.List;

import com.cdhaixun.domain.Business;

public interface BusinessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Business record);

    int insertSelective(Business record);

    Business selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Business record);

    int updateByPrimaryKey(Business record);

    List<Business> listBusiness();

    List<Business> selectByBusiness(Business business);
}