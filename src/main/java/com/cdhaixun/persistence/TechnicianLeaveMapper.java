package com.cdhaixun.persistence;

import com.cdhaixun.domain.TechnicianLeave;

import java.util.List;

public interface TechnicianLeaveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TechnicianLeave record);

    int insertSelective(TechnicianLeave record);

    TechnicianLeave selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TechnicianLeave record);

    int updateByPrimaryKey(TechnicianLeave record);

    List<TechnicianLeave> selectTechnicianLeaveList();

    void deleteByTechnicianLeaveId(int technicianId);

   List<TechnicianLeave>  selectOneByTechnicianLeave(TechnicianLeave technicianLeave);
}