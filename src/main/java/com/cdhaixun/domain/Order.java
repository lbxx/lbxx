package com.cdhaixun.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order  implements Serializable{
    private Integer id;

    private BigDecimal amount;

    private BigDecimal payment;

    private BigDecimal privilege;

    private Integer storeid;

    private Integer state;

    private Date createtime;

    private Integer userid;

    private Integer managerid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getPrivilege() {
        return privilege;
    }

    public void setPrivilege(BigDecimal privilege) {
        this.privilege = privilege;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getManagerid() {
        return managerid;
    }

    public void setManagerid(Integer managerid) {
        this.managerid = managerid;
    }
}