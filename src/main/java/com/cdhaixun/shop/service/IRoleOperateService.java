package com.cdhaixun.shop.service;

import com.cdhaixun.domain.RoleOperate;

import java.util.List;

/**
 * Created by Administrator on 2017-06-24.
 */
public interface IRoleOperateService {
    /**
     * 根据角色查询操作权限
     * @param role
     * @return
     */
    List<RoleOperate> findByRole(String role);
}
