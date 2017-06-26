package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.RoleOperate;
import com.cdhaixun.persistence.RoleOperateMapper;
import com.cdhaixun.shop.service.IRoleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017-06-24.
 */
@Service
public class RoleOperateServiceImpl implements IRoleOperateService {
    @Autowired
    private RoleOperateMapper roleOperateMapper;
    @Override
    public List<RoleOperate> findByRole(String role) {
        return roleOperateMapper.selectByRole(role);
    }
}
