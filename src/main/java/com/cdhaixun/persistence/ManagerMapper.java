package com.cdhaixun.persistence;

import com.cdhaixun.common.persistence.BaseMapper;
import com.cdhaixun.domain.Manager;
import com.cdhaixun.vo.PermissionVo;

import java.util.List;

public interface ManagerMapper extends BaseMapper<Manager>{
    List<Manager> selectByManager(Manager manager);
    Manager    selectOneByManager(Manager manager);

    List<PermissionVo> getPermissionList(String role);
}