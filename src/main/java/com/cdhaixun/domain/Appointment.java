package com.cdhaixun.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Appointment {
    private Integer id;

    private Date createtime;

    private Integer userid;

    private Integer storeid;

    private String state;
    private  Date starttime; //预约开始时间
    private Date starttimeFrom;
    private Date starttimeTo;
    private  Date endtime;//结束时间
    private  Date createtimeFrom ;
    private  Date createtimeTo ;
    private  Integer appointnumber;//预约人数
    private List<Business> businessList;
    private List<Baby> babyList;
    private Technician technician;
    private  Store store;
    private String orderBy;
    private Integer potionamount;
    private BigDecimal potionprice;
    private Integer  paystate;
    private Integer potionid;
    private BigDecimal totalprice;
    private Date paytime;

    public Integer getPotionamount() {
        return potionamount;
    }

    public void setPotionamount(Integer potionamount) {
        this.potionamount = potionamount;
    }

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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<Business> getBusinessList() {
        return businessList;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public void setBusinessList(List<Business> businessList) {
        this.businessList = businessList;
    }

    public List<Baby> getBabyList() {
        return babyList;
    }

    public void setBabyList(List<Baby> babyList) {
        this.babyList = babyList;
    }

    public Integer getAppointnumber() {
        return appointnumber;
    }

    public void setAppointnumber(Integer appointnumber) {
        this.appointnumber = appointnumber;
    }

    public Date getStarttimeFrom() {
        return starttimeFrom;
    }

    public void setStarttimeFrom(Date starttimeFrom) {
        this.starttimeFrom = starttimeFrom;
    }

    public Date getStarttimeTo() {
        return starttimeTo;
    }

    public void setStarttimeTo(Date starttimeTo) {
        this.starttimeTo = starttimeTo;
    }

    public Date getCreatetimeFrom() {
        return createtimeFrom;
    }

    public void setCreatetimeFrom(Date createtimeFrom) {
        this.createtimeFrom = createtimeFrom;
    }

    public Date getCreatetimeTo() {
        return createtimeTo;
    }

    public void setCreatetimeTo(Date createtimeTo) {
        this.createtimeTo = createtimeTo;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Date getStarttime() {

        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    private Boolean isdelete;
    private String remark;
    private  Integer technicianid;

    public Integer getTechnicianid() {
        return technicianid;
    }

    public void setTechnicianid(Integer technicianid) {
        this.technicianid = technicianid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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