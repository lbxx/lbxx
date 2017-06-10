package com.cdhaixun.domain;

import java.io.Serializable;

/**
 * 角色-操作
 * @Author tanggm
 * @Date 2017/6/6 23:41
 */
public class RoleOperate implements Serializable{
    /**
     * 角色编码
     */
    private String role;
    /**
     * 权限
     */
    private Integer operateid;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

	public Integer getOperateid() {
		return operateid;
	}

	public void setOperateid(Integer operateid) {
		this.operateid = operateid;
	}

    
}