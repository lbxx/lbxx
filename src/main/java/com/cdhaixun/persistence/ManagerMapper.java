package com.cdhaixun.persistence;

import com.cdhaixun.common.persistence.BaseMapper;
import com.cdhaixun.domain.Manager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ManagerMapper extends BaseMapper<Manager>{
    List<Manager> selectByManager(Manager manager);
    Manager    selectOneByManager(Manager manager);
}