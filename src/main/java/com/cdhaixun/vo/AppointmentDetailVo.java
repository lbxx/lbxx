package com.cdhaixun.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 预约详情Vo
 * @Author tanggm
 * @Date 2017/7/6 22:47
 */
public class AppointmentDetailVo {
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String uname;
    private String bname;
    private String tname;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String starttime;
    private String solution;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
