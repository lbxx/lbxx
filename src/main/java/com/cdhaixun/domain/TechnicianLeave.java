package com.cdhaixun.domain;

import java.util.Date;

public class TechnicianLeave {
    private Integer id;

    private Integer technicianid;

    private Date leaveday;

    private Date createtime;

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

    public Date getLeaveday() {
        return leaveday;
    }

    public void setLeaveday(Date leaveday) {
        this.leaveday = leaveday;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}