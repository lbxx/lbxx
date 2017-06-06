package com.cdhaixun.common.redisVo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tangxinmao on 2017/6/2.
 */
public class Captcha  implements Serializable{
    private String captcha;//验证码
    private Date createTime;// 创建时间

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
