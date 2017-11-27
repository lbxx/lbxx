package com.cdhaixun.shop.web;

import com.cdhaixun.common.appVo.Mobile;
import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.common.appVo.User;
import com.cdhaixun.common.emun.Code;
import com.cdhaixun.common.redisVo.Captcha;
import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.shop.service.IUserService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017-07-01.
 */
@Controller
@RequestMapping("userApp")
public class UserAppController extends BaseController {
    @Autowired
    private IUserService userService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping(value = "modifyUser", method = RequestMethod.POST)
    @ResponseBody
    public Result modifyUser(@RequestBody User user) throws Exception {
        Result result = new Result();
        com.cdhaixun.domain.User userDb=new  com.cdhaixun.domain.User();
        ConvertUtils.register(new DateConverter(null), java.util.Date.class);
        ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
        BeanUtils.copyProperties(userDb,user);
        userService.update(userDb);
        result.setResult(true);
        return result;
    }

    @RequestMapping(value = "modifyMobile", method = RequestMethod.POST)
    @ResponseBody
    public Result modifyUser(@RequestBody Mobile mobile) throws Exception {
        Result result = new Result();
        com.cdhaixun.domain.User userDb=new  com.cdhaixun.domain.User();
        Captcha captcha = (Captcha) redisTemplate.boundHashOps("captcha").entries().remove(mobile.getMobile());
        if(captcha!=null&&captcha.getCaptcha().equals(mobile.getCaptcha())&&(new Date().getTime()-captcha.getCreateTime().getTime())<3*60*1000) {
            userDb.setMobile(mobile.getMobile());
            userService.update(userDb);
            result.setResult(true);
        }else{
            result.setCode(Code.CAPTCHA_ERROR);
        }
        return result;
    }



}
