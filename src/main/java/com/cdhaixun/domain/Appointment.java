package com.cdhaixun.domain;

import java.util.Date;

public class Appointment {
    private Integer id;

    private Date createtime;

    private Integer userid;

    private Integer storeid;

    private String state;
    private  Date starttime; //预约开始时间
    private  Date endtime;//结束时间
    private  Date createtimeFrom ;
    private  Date createtimeTo ;
    private  Integer appointnumber;//预约人数

    public Integer getAppointnumber() {
        return appointnumber;
    }

    public void setAppointnumber(Integer appointnumber) {
        this.appointnumber = appointnumber;
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