package com.cdhaixun.domain;

import java.math.BigDecimal;
import java.util.Date;

public class UserType {
    private Integer id;

    private String typename;

    private BigDecimal rebate;

    private Integer points;

    private Integer state;
    private Integer isdelete;
    private Integer chainstoreid;
    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public BigDecimal getRebate() {
        return rebate;
    }

    public void setRebate(BigDecimal rebate) {
        this.rebate = rebate;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public Integer getChainstoreid() {
        return chainstoreid;
    }

    public void setChainstoreid(Integer chainstoreid) {
        this.chainstoreid = chainstoreid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}