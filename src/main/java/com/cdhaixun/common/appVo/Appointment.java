package com.cdhaixun.common.appVo;

import com.cdhaixun.deserializer.DateJsonDeserializer;
import com.cdhaixun.domain.Baby;
import com.cdhaixun.domain.Business;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Appointment implements Serializable{

    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = DateJsonDeserializer.class)
    private Date createtime;

    private Integer userid;
    private String remark;

    private Integer storeid;

    private String state;

    private Boolean isdelete;
    private Integer badyCount;//宝宝数量
    private Integer technicianid;//技师
    private String starttime; //预约时间段

    private String endtime;

    List<Baby> babyList;//宝宝
    List<Business> businessList;//所选业务
    private Integer potionamount;
    private BigDecimal potionprice;
    private Integer paystate;
    private Integer potionid;
    private BigDecimal totalprice;
    private Date paytime;

    public BigDecimal getPotionprice() {
        return potionprice;
    }

    public void setPotionprice(BigDecimal potionprice) {
        this.potionprice = potionprice;
    }

    public Integer getPaystate() {
        return paystate;
    }

    public void setPaystate(Integer paystate) {
        this.paystate = paystate;
    }

    public Integer getPotionid() {
        return potionid;
    }

    public void setPotionid(Integer potionid) {
        this.potionid = potionid;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public Integer getPotionamount() {

        return potionamount;
    }

    public void setPotionamount(Integer potionamount) {
        this.potionamount = potionamount;
    }

    public void setBabyList(List<Baby> babyList) {
        this.babyList = babyList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public List<Business> getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List<Business> businessList) {
        this.businessList = businessList;
    }

    public List<Baby> getBabyList() {
        return babyList;
    }


    public Integer getTechnicianid() {
        return technicianid;
    }

    public void setTechnicianid(Integer technicianid) {
        this.technicianid = technicianid;
    }

    public Integer getBadyCount() {
        return badyCount;
    }

    public void setBadyCount(Integer badyCount) {
        this.badyCount = badyCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Boolean getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }
}