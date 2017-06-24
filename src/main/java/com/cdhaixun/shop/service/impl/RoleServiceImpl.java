package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.Role;
import com.cdhaixun.persistence.RoleMapper;
import com.cdhaixun.shop.service.IRoleSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017-06-24.
 */
@Service
public class RoleServiceImpl implements IRoleSevice {
@Autowired
private RoleMapper roleMapper;
    @Override
    public List<Role> findAll() {
        return roleMapper.selectAll();
    }
}
