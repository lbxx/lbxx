package com.cdhaixun.domain;

import java.io.Serializable;

/**
 * 操作
 * @Author tanggm
 * @Date 2017/6/6 23:41
 */
public class Operate implements Serializable {
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
    private Integer menu;
    /**
     * 操作
     */
    private String op;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
    public Integer getMenu() {
        return menu;
    }

    public void setMenu(Integer menu) {
        this.menu = menu;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op == null ? null : op.trim();
    }
}