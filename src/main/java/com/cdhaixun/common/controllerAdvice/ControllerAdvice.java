package com.cdhaixun.common.controllerAdvice;

import com.cdhaixun.common.appVo.Result;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    private static Log logger = LogFactory.getLog(ControllerAdvice.class);

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Result exception(RuntimeException e) {
        Result result = new Result();
        result.setResult(false);
        logger.error("服务器发生异常！............................",e);
        StackTraceElement stackTraceElement= e.getStackTrace()[0];
        result.setMsg(e.toString()+"---"+stackTraceElement.getClassName()+"--"+stackTraceElement.getMethodName()+"--"+stackTraceElement.getLineNumber());
        result.setData("服务器发生异常！............");
        return result;
    }
}
