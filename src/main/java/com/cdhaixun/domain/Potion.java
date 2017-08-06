package com.cdhaixun.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Potion {
    private Integer id;

    private Integer potioncategoryid;

    private String name;

    private BigDecimal price;

    private Date createtime;

    private Boolean isdelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPotioncategoryid() {
        return potioncategoryid;
    }

    public void setPotioncategoryid(Integer potioncategoryid) {
        this.potioncategoryid = potioncategoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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