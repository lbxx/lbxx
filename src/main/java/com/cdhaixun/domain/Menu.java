package com.cdhaixun.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单
 * @Author tanggm
 * @Date 2017/6/6 23:41
 */
public class Menu  implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 菜单名
     */
    private String name;
    /**
     * 状态
     */
    private Boolean state;
    /**
     * 菜单pid
     */
    private Integer parentid;
    /**
     * 排序，越小越在前
     */
    private Integer sort;
    /**
     * url菜单链接
     */
    private String url;
    
    private List<Menu> menuList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
    
}