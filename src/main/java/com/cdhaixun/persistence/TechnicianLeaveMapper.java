package com.cdhaixun.persistence;

import com.cdhaixun.domain.TechnicianLeave;

public interface TechnicianLeaveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TechnicianLeave record);

    int insertSelective(TechnicianLeave record);

    TechnicianLeave selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TechnicianLeave record);

    int updateByPrimaryKey(TechnicianLeave record);
}