package com.cdhaixun.domain;

import java.util.Date;

public class PayInfo {
    private Integer id;

    private String appid;

    private String chnltrxid;

    private String cusid;

    private String fintime;

    private String reqsn;

    private String sign;

    private String trxid;

    private String trxstatus;

    private String retmsg;

    private String errmsg;

    private String payinfo;

    private String retcode;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getChnltrxid() {
        return chnltrxid;
    }

    public void setChnltrxid(String chnltrxid) {
        this.chnltrxid = chnltrxid == null ? null : chnltrxid.trim();
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid == null ? null : cusid.trim();
    }

    public String getFintime() {
        return fintime;
    }

    public void setFintime(String fintime) {
        this.fintime = fintime == null ? null : fintime.trim();
    }

    public String getReqsn() {
        return reqsn;
    }

    public void setReqsn(String reqsn) {
        this.reqsn = reqsn == null ? null : reqsn.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getTrxid() {
        return trxid;
    }

    public void setTrxid(String trxid) {
        this.trxid = trxid == null ? null : trxid.trim();
    }

    public String getTrxstatus() {
        return trxstatus;
    }

    public void setTrxstatus(String trxstatus) {
        this.trxstatus = trxstatus == null ? null : trxstatus.trim();
    }

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg == null ? null : retmsg.trim();
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg == null ? null : errmsg.trim();
    }

    public String getPayinfo() {
        return payinfo;
    }

    public void setPayinfo(String payinfo) {
        this.payinfo = payinfo == null ? null : payinfo.trim();
    }

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode == null ? null : retcode.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}