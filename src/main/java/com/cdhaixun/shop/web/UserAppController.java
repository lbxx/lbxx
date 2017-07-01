package com.cdhaixun.shop.web;

import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.domain.User;
import com.cdhaixun.shop.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017-07-01.
 */
@Controller
@RequestMapping("userApp")
public class UserAppController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "modifyUser", method = RequestMethod.POST)
    @ResponseBody
    public Result modifyUser(@RequestBody User user) throws Exception {
        Result result = new Result();
        userService.update(user);
        result.setResult(true);
        return result;
    }
}
