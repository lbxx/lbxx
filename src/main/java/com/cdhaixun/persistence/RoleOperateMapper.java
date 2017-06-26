package com.cdhaixun.persistence;

import com.cdhaixun.common.persistence.BaseMapper;
import com.cdhaixun.domain.RoleOperate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleOperateMapper extends BaseMapper {
    List<RoleOperate> selectByRole(@Param(value = "role") String role);
}