package com.cdhaixun.shop.service;

import com.cdhaixun.domain.Role;

import java.util.List;

/**
 * Created by Administrator on 2017-06-24.
 */
public interface IRoleSevice {
    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll();

    Role findById(Integer id);

    int savePermission(String permissions, String code);
}
