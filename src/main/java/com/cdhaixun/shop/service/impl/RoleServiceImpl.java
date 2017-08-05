package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.Role;
import com.cdhaixun.domain.RoleMenu;
import com.cdhaixun.domain.RoleOperate;
import com.cdhaixun.persistence.RoleMapper;
import com.cdhaixun.persistence.RoleOperateMapper;
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
    @Autowired
    private RoleOperateMapper roleOperateMapper;
    @Override
    public List<Role> findAll() {
        return roleMapper.selectAll();
    }

    @Override
    public Role findById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int savePermission(String permissions, String code) {
        int i = 0;
        // 删除原有的role_menu表数据
        i = roleMapper.removeMenuByCode(code);
        // 删除原有的role_operator表数据
        i = roleMapper.removePermissionByCode(code);
        // 新增
        String[] perarr = permissions.split(",");
        for (String str:perarr) {
            String[] ipms = str.split("&");
            // 获取id
            String idstr = ipms[1];
            // 判断idstr 如果是正常的一级、二级菜单则是id值，如果包含有后面 三个 “000”,则是权限id值
            if(idstr.contains("000")){
                RoleOperate roleOperate = new RoleOperate();
                roleOperate.setRole(code);
                roleOperate.setOperateid(Integer.valueOf(idstr.substring(0, idstr.length()-3)));
                i = roleOperateMapper.insert(roleOperate);
            }else{
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuid(Integer.valueOf(idstr));
                roleMenu.setRole(code);
                i = roleOperateMapper.saveRoleMenu(roleMenu);
            }
        }
        return i;
    }
}
