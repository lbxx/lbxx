package com.cdhaixun.domain;

import java.util.Date;
import java.util.List;

public class TechnicianBusiness {
    private Integer id;

    private Integer technicianid;

    private Integer businessid;

    private Integer spend;

    private Date createtime;

    private Boolean isdelete;
    private List<Integer> businessidList;

    public List<Integer> getBusinessidList() {
        return businessidList;
    }

    public void setBusinessidList(List<Integer> businessidList) {
        this.businessidList = businessidList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTechnicianid() {
        return technicianid;
    }

    public void setTechnicianid(Integer technicianid) {
        this.technicianid = technicianid;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public Integer getSpend() {
        return spend;
    }

    public void setSpend(Integer spend) {
        this.spend = spend;
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