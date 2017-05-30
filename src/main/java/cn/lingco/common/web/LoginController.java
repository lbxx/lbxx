package cn.lingco.common.web;

import cn.lingco.common.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLXML;

/**
 * Created by tangxinmao on 2017/5/30.
 */
@Controller
@RequestMapping(value = "login")
public class LoginController extends  BaseController {
    /**
     * 登陆
     * @return
     */
    @RequestMapping(value = "login")
    @ResponseBody
    public Result login(){
        Result result=new Result();
        result.setResult(true);
        return  result;
    }
}
