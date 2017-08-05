package com.cdhaixun.persistence;

import com.cdhaixun.common.persistence.BaseMapper;
import com.cdhaixun.domain.RoleMenu;
import com.cdhaixun.domain.RoleOperate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleOperateMapper extends BaseMapper {
    List<RoleOperate> selectByRole(@Param(value = "role") String role);

    /**
     * 添加角色菜单表
     * @param roleMenu
     * @return
     */
    int saveRoleMenu(RoleMenu roleMenu);
}