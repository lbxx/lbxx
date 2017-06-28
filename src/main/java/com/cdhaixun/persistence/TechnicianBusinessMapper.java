package com.cdhaixun.persistence;

import com.cdhaixun.domain.TechnicianBusiness;

public interface TechnicianBusinessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TechnicianBusiness record);

    int insertSelective(TechnicianBusiness record);

    TechnicianBusiness selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TechnicianBusiness record);

    int updateByPrimaryKey(TechnicianBusiness record);
}