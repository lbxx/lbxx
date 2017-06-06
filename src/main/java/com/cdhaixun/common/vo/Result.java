package com.cdhaixun.common.vo;

import com.cdhaixun.common.emun.Code;

/**
 * Created by tangxinmao on 2017/5/30.
 */
public class Result  {
    private String msg;
    private boolean result;
    private Code code;

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
