package com.cdhaixun.persistence;

import com.cdhaixun.common.persistence.BaseMapper;
import com.cdhaixun.domain.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper {

    List<Role> selectAll();
}