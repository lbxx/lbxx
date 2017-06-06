package com.cdhaixun.domain;

import java.io.Serializable;

/**
 * 角色
 * @Author tanggm
 * @Date 2017/6/6 23:41
 */
public class Role  implements Serializable {
    /**
     * 角色编码
     */
    private String code;
    /**
     * id
     */
    private Integer id;
    /**
     * 名字
     */
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

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
}