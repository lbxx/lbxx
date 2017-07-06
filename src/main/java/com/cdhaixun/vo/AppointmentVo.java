package com.cdhaixun.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Author tanggm
 * @Date 2017/7/6 22:47
 */
public class AppointmentVo {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String acreatetime;
    private String uname;
    private String sname;
    private String tname;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String aendtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String astarttime;

    public String getAcreatetime() {
        return acreatetime;
    }

    public void setAcreatetime(String acreatetime) {
        this.acreatetime = acreatetime;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getAendtime() {
        return aendtime;
    }

    public void setAendtime(String aendtime) {
        this.aendtime = aendtime;
    }

    public String getAstarttime() {
        return astarttime;
    }

    public void setAstarttime(String astarttime) {
        this.astarttime = astarttime;
    }
}
