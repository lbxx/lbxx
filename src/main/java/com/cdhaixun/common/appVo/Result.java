package com.cdhaixun.common.appVo;

import com.cdhaixun.common.emun.Code;

import java.io.Serializable;

/**
 * Created by tangxinmao on 2017/5/30.
 */
public class Result<T>  implements Serializable{
    private String msg;//消息体
    private boolean result;//结果
    private Code code;//错误码
    private T data;//返回消息

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }
}
