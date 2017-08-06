package com.cdhaixun.domain;

import java.util.Date;
import java.util.List;

public class PotionCategory {
    private Integer id;

    private String name;

    private Date createtime;

    private Boolean isdelete;

    private List<Potion> potionList;

    public List<Potion> getPotionList() {
        return potionList;
    }

    public void setPotionList(List<Potion> potionList) {
        this.potionList = potionList;
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Boolean getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }
}