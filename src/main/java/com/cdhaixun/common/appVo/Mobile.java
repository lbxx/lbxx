package com.cdhaixun.common.appVo;

import java.io.Serializable;

/**
 * Created by tangxinmao on 2017/6/11.
 * 用于发送验证码
 */
public class Mobile implements Serializable {
   private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
