package com.cdhaixun.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonMsgUtil {
    /**
     * (错误) 请求内容，为业务失败消息
     */
    public static int SYS_MSG_BAD_REQUEST          = 400;
    /**
     * (成功) 服务器已成功处理了请求。通常，这表示服务器提供了请求的网页。请求的内容成功
     */
    public static int SYS_MSG_OK                   = 200;
    /**
     * (未授权) 请求要求身份验证。 对于需要登录的网页，服务器可能返回此响应。
     */
    public static int SYS_MSG_UNAUTHORIZED         = 401;
    /**
     * (禁止) 服务器拒绝请求,已登陆，但角色无法访问此资源
     */
    public static int SYS_MSG_FORBIDDEN            = 403;
    /**
     * (服务器内部错误) 服务器遇到错误，无法完成请求。服务器异常。
     */
    public static int SYS_MSG_INTERNAL_SERVERERROR = 500;
    /**
     * (网关超时) 服务器作为网关或代理，但是没有及时从上游服务器收到请求。
     */
    public static int SYS_MSG_GATEWAY_TIMEOUT      = 504;
    /**
     * 构造成功消息
     * 
     * @param msg
     * @return
     */
    public static Object getSuccessJsonMsg(Object object, String msg) {
        Map<String, Object> pageMap = new HashMap<String, Object>();
        pageMap.put("ok", true);
        pageMap.put("msg", msg);
        pageMap.put("item", object);
        return pageMap;
    }

    /**
     * 成功消息
     * 
     * @param msg
     * @return
     */
    public static Object getSuccessJsonMsg(String msg) {
        Map<String, Object> pageMap = new HashMap<String, Object>();
        pageMap.put("ok", true);
        pageMap.put("code", SYS_MSG_OK);
        pageMap.put("msg", msg);
        return pageMap;
    }

    /**
     * 失败消息
     * 
     * @param msg
     * @return
     */
    public static Object getFailJsonMsg(String msg) {
        Map<String, Object> pageMap = new HashMap<String, Object>();
        pageMap.put("ok", false);
        pageMap.put("code", SYS_MSG_BAD_REQUEST);
        pageMap.put("msg", msg);
        return pageMap;
    }
    /**
     * 系统级错误
     * @return
     */
    public static Object getFailJsonSysMsg() {
        return getFailJsonMsg("系统错误，请联系系统管理员");
    }

    /**
     * 未登录提示
     * 
     * @return
     */
    public static Map<String, Object> getFailUnauthorizedJsonMsg() {
        Map<String, Object> pageMap = new HashMap<String, Object>();
        pageMap.put("ok", false);
        pageMap.put("msg", "请登录");
        pageMap.put("code", SYS_MSG_UNAUTHORIZED);
        return pageMap;
    }

    /**
     * 公共失败消息
     * 
     * @param msg
     * @param code
     * @return
     */
    public static Map<String, Object> getCommonFailJsonMsg(String msg,
            int code) {
        Map<String, Object> pageMap = new HashMap<String, Object>();
        pageMap.put("ok", false);
        pageMap.put("msg", msg);
        pageMap.put("code", code);
        return pageMap;
    }

    /**
     * 公共成功消息
     * 
     * @param msg
     * @param
     * @return
     */
    public static Map<String, Object> getCommonSuccessJsonMsg(String msg,
            Object obj) {
        Map<String, Object> pageMap = new HashMap<String, Object>();
        pageMap.put("ok", true);
        pageMap.put("msg", msg);
        pageMap.put("code", SYS_MSG_OK);
        pageMap.put("object", obj);
        return pageMap;
    }
    /**
     * 公共成功消息-带分页
     * @param msg
     * @param
     * @return
     */
    public static Map<String, Object> getCommonSuccessPageJsonMsg(String msg, Long total, List<?> rows){
        Map<String, Object> pageMap = new HashMap<String, Object>();
        pageMap.put("ok", true);
        pageMap.put("msg", msg);
        pageMap.put("code", SYS_MSG_OK);
        pageMap.put("total", total);
        pageMap.put("rows", rows);
        return pageMap;
    }
}