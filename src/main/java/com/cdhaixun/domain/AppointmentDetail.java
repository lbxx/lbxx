package com.cdhaixun.domain;

import java.util.Date;

public class AppointmentDetail {
    private Integer id;

    private Integer userid;

    private Integer babyid;

    private Integer technicianid;

    private Integer bussinessid;

    private Date starttime;

    private Date endtime;

    private Integer appointmentid;

    private String solution;

    private Integer timebucketid;

    private Boolean isdelete;
    private  Date createtimeFrom ;
    private  Date createtimeTo ;
    private  Business business;
    private  Baby baby;

    public Baby getBaby() {
        return baby;
    }

    public void setBaby(Baby baby) {
        this.baby = baby;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getBabyid() {
        return babyid;
    }

    public void setBabyid(Integer babyid) {
        this.babyid = babyid;
    }

    public Integer getTechnicianid() {
        return technicianid;
    }

    public void setTechnicianid(Integer technicianid) {
        this.technicianid = technicianid;
    }

    public Integer getBussinessid() {
        return bussinessid;
    }

    public void setBussinessid(Integer bussinessid) {
        this.bussinessid = bussinessid;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getAppointmentid() {
        return appointmentid;
    }

    public void setAppointmentid(Integer appointmentid) {
        this.appointmentid = appointmentid;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution == null ? null : solution.trim();
    }

    public Integer getTimebucketid() {
        return timebucketid;
    }

    public void setTimebucketid(Integer timebucketid) {
        this.timebucketid = timebucketid;
    }

    public Boolean getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }
}