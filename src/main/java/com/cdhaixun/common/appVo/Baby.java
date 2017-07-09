package com.cdhaixun.common.appVo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Baby {
    private Integer id;

    private String name;

    @JsonFormat(pattern="yyyy.MM.dd")
    private Date birthday;
    private Integer birthDate;
    private Integer birthMonth;
    private Integer birthYear;
    private Integer year;

    private Boolean gender;

    private Date createtime;

    private Integer userid;

    private Boolean isdelete;
    private String remark;
    private Integer babyid;

    public Integer getBabyid() {
        return babyid;
    }

    public void setBabyid(Integer babyid) {
        this.babyid = babyid;
    }

    public String getRemark() {
        return remark;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Integer birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(Integer birthMonth) {
        this.birthMonth = birthMonth;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
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

    public Boolean getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }
}