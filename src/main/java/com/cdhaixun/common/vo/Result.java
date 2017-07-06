package com.cdhaixun.common.vo;

import com.cdhaixun.common.emun.Code;

/**
 * Created by tangxinmao on 2017/5/30.
 */
public class Result<T>  {
    private String msg;
    private boolean result;
    private Code code;
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
