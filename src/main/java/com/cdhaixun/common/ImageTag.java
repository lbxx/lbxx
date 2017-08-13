package com.cdhaixun.common;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by tangxinmao on 2016/11/30.
 */
public class ImageTag extends SimpleTagSupport implements ApplicationContextAware {
//    private static RedisTemplate<String, String> redisTemplate;
    @Value("#{configProperties['domainName']}")
    private String domainName;
    StringWriter sw = new StringWriter();
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void doTag() throws JspException, IOException {

        if (key != null) {
            JspWriter out = getJspContext().getOut();
            //云服务器地址
            out.println(domainName + key);
        } else {
          /* 控制标签体内容 */
            getJspBody().invoke(sw);
            /*标签内容*/
            getJspContext().getOut().println(sw.toString());
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        redisTemplate = (RedisTemplate<String, String>) applicationContext.getBean("redisTemplate");
    }
}
