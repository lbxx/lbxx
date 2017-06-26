package com.cdhaixun.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ConfigContentUtils {
    /**
     * 图片域名1
     */
    public static final String PIC_DOMAIN = getString("PIC_DOMAIN");
    /**
     * 图片存储的根目录
     */
    public static final String PIC_ROOT_PATH = getString("PIC_ROOT_PATH");
    /**
     * 图片项目名称
     */
    public static final String PIC_PROJECT_NAME = getString("PIC_PROJECT_NAME");
    /**
     * 系统域名
     */
    public static final String SYS_DOMAIN = getString("SYS_DOMAIN");
    /**
     * SHOP上下文
     */
    public static final String SHOP_CONTEXT = getString("SHOP_CONTEXT");
    /**
     * SHOPWAP上下文
     */
    public static final String SHOPWAP_CONTEXT = getString("SHOPWAP_CONTEXT");

    public static String buildImageUrl(String imgeContent){
        if(StringUtil.isNotBlank(imgeContent)){
            return ConfigContentUtils.PIC_DOMAIN + imgeContent;
        }else{
            return "";
        }
    }

    /**
     * 获取资源文件中的资源
     * @param propsvalue
     * @return
     */
    public static String getString(String propsvalue){
        Properties props;
        try {
            props = PropertiesLoaderUtils.loadAllProperties("projectUrl.properties");
        } catch (IOException e) {
            return "";
        } 
        return props.getProperty(propsvalue);
    }
    /**
     * 获取资源文件中的资源
     * @param propsvalue 资源key
     * @param propsvalue 资源文件名
     * @return
     */
    public static String getString(String propsvalue, String prolocal){
        Properties props;
        try {
            props = PropertiesLoaderUtils.loadAllProperties(prolocal);
        } catch (IOException e) {
            return "";
        }
        return props.getProperty(propsvalue);
    }
}