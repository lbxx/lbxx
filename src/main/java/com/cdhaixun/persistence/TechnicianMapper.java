package com.cdhaixun.persistence;

import java.util.List;

import com.cdhaixun.domain.Technician;

public interface TechnicianMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Technician record);

    int insertSelective(Technician record);

    Technician selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Technician record);

    int updateByPrimaryKey(Technician record);
    
    List<Technician> selectTechnicianList();

    List<Technician> selectByTechnician(Technician technician);

    void deleteByTechnicianId(Integer id);
}