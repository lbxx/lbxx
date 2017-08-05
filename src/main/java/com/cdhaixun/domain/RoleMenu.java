package com.cdhaixun.domain;

import java.io.Serializable;

/**
 * 角色-菜单
 * @Author tanggm
 * @Date 2017/8/5 16:41
 */
public class RoleMenu implements Serializable{
    /**
     * 角色
     */
    private String role;
    /**
     * 菜单id
     */
    private Integer menuid;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }
}