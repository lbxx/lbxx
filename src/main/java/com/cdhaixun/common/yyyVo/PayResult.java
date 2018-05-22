package com.cdhaixun.common.yyyVo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by tangxinmao on 2017/6/23.
 */
/*@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)*/
public class PayResult {
    private String acct;
    private String appid;
    private String chnltrxid;
    private String cusid;
    private String fintime;
    private String reqsn;
    private String sign;
    private String trxcode;
    private String trxid;
    private String trxstatus;
    private String retcode;
    private String retmsg;
    private  String errmsg;
    private String randomstr;
    private  String payinfo;//支付成功

    public String getRandomstr() {
        return randomstr;
    }

    public void setRandomstr(String randomstr) {
        this.randomstr = randomstr;
    }

    public String getPayinfo() {
        return payinfo;
    }

    public void setPayinfo(String payinfo) {
        this.payinfo = payinfo;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

    public String getAcct() {
        return acct;
    }

    public void setAcct(String acct) {
        this.acct = acct;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getChnltrxid() {
        return chnltrxid;
    }

    public void setChnltrxid(String chnltrxid) {
        this.chnltrxid = chnltrxid;
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    public String getFintime() {
        return fintime;
    }

    public void setFintime(String fintime) {
        this.fintime = fintime;
    }

    public String getReqsn() {
        return reqsn;
    }

    public void setReqsn(String reqsn) {
        this.reqsn = reqsn;
    }

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTrxcode() {
        return trxcode;
    }

    public void setTrxcode(String trxcode) {
        this.trxcode = trxcode;
    }

    public String getTrxid() {
        return trxid;
    }

    public void setTrxid(String trxid) {
        this.trxid = trxid;
    }

    public String getTrxstatus() {
        return trxstatus;
    }

    public void setTrxstatus(String trxstatus) {
        this.trxstatus = trxstatus;
    }
}
