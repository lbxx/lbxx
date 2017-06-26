package com.cdhaixun.domain;

import java.io.Serializable;

/**
 * 操作
 * @Author tanggm
 * @Date 2017/6/6 23:41
 */
public class Operate implements Serializable {
    private Integer id;
    /**
     * 操作名
     */
    private String name;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 是否显示  1 显示  0 隐藏
     */
    private Integer isshow;
    /**
     * 菜单
     */
    private Integer menuid;
    /**
     * 操作
     */
    private String permission;

     private String menucode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMenucode() {
        return menucode;
    }

    public void setMenucode(String menucode) {
        this.menucode = menucode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsshow() {
        return isshow;
    }

    public void setIsshow(Integer isshow) {
        this.isshow = isshow;
    }

	public Integer getMenuid() {
		return menuid;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
    
}