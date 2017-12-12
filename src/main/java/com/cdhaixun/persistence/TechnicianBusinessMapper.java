package com.cdhaixun.persistence;

import com.cdhaixun.domain.TechnicianBusiness;

import java.util.List;
import java.util.Map;

public interface TechnicianBusinessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TechnicianBusiness record);

    int insertSelective(TechnicianBusiness record);

    TechnicianBusiness selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TechnicianBusiness record);

    int updateByPrimaryKey(TechnicianBusiness record);

    List<TechnicianBusiness> selectByTechnicianBusiness(TechnicianBusiness technicianBusiness,Integer storeId);

    TechnicianBusiness selectOneByTechnicianBusiness(TechnicianBusiness technicianBusiness);

    List<TechnicianBusiness> selectByTechnicianId(Integer technicianId);

    int deleteByTechnicianId(Integer id);

    List<TechnicianBusiness> selectByTechnicianBusinessAndStoreId(TechnicianBusiness technicianBusiness,
            Integer storeId);

    List<TechnicianBusiness> selectByTechnicianBusinessAndStoreId(Map<String, Object> params);

}