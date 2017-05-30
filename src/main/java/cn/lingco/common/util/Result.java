package cn.lingco.common.util;

import cn.lingco.common.emun.Code;
import com.sun.tools.javac.main.Main;

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
