package com.cdhaixun.common.appVo;

import java.io.Serializable;

/**
 * Created by tangxinmao on 2017/6/11.
 * 用于发送验证码和验证码登录
 */
public class Mobile implements Serializable {
    private String mobile;
    private String captcha;//验证码

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
