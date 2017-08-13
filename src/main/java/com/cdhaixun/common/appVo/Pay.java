package com.cdhaixun.common.appVo;

/**
 * Created by tangxinmao on 2017/6/23.
 */
public class Pay {
    private String appid;//应用id
    private String authcode;//支付码
    private String body;//商品名
    private String cusid;//商家id
    private String key;//key
    private String randomstr;//随机数
    private String remark;//备注
    private String reqsn;//订单号
    private Long trxamt;//支付金额
    private String  sign;//签名
    private String paytype;//支付方式
    private String notify_url;//微信回调url
    private String sub_appid;//子商户appId
    private String sub_mchid;//子商户id
    private Integer storeid;

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public String getSub_appid() {
        return sub_appid;
    }

    public void setSub_appid(String sub_appid) {
        this.sub_appid = sub_appid;
    }

    public String getSub_mchid() {
        return sub_mchid;
    }

    public void setSub_mchid(String sub_mchid) {
        this.sub_mchid = sub_mchid;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public Long getTrxamt() {
        return trxamt;
    }

    public void setTrxamt(Long trxamt) {
        this.trxamt = trxamt;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAuthcode() {
        return authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRandomstr() {
        return randomstr;
    }

    public void setRandomstr(String randomstr) {
        this.randomstr = randomstr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReqsn() {
        return reqsn;
    }

    public void setReqsn(String reqsn) {
        this.reqsn = reqsn;
    }


}
