package com.cdhaixun.domain;

import java.util.Date;

public class TechnicianLeave {
    private Integer id;

    private Integer technicianid;

    private Date starttime;

    private Date endtime;

    private Date createtime;

    private Boolean isdelete;

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