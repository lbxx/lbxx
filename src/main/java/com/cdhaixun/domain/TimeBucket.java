package com.cdhaixun.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TimeBucket {
    private Integer id;
    @JsonFormat(pattern="HH:mm:ss")
    private Date starttime;
    @JsonFormat(pattern="HH:mm:ss")
    private Date endtime;

    private Integer storeid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }
}