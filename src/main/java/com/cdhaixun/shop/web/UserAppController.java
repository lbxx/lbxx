package com.cdhaixun.shop.web;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cdhaixun.common.appVo.Mobile;
import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.common.appVo.User;
import com.cdhaixun.common.emun.Code;
import com.cdhaixun.common.redisVo.Captcha;
import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.domain.Baby;
import com.cdhaixun.domain.Car;
import com.cdhaixun.shop.service.IBabyService;
import com.cdhaixun.shop.service.ICarService;
import com.cdhaixun.shop.service.IUploadService;
import com.cdhaixun.shop.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Created by Administrator on 2017-07-01.
 */
@Controller
@RequestMapping("userApp")
@Api(value="用户controller")
public class UserAppController extends BaseController {
    @Autowired
    private IUserService userService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private IUploadService uploadService;
    @Autowired
    private IBabyService babyService;
    @Autowired
    private ICarService carService;

    @RequestMapping(value = "modifyAvatar", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value="获取用户信息",notes="注意表单提交,传递用户id")
    public Result modifyAvatar(@ApiParam(required = true, name = "user",value = "用户信息") User user,  @ApiParam(required = true, name = "file",value = "头像文件") @RequestParam(name = "file")  MultipartFile file, HttpServletRequest httpServletRequest) throws Exception {
        Result result = new Result();
        com.cdhaixun.common.vo.Result upload = uploadService.upload(httpServletRequest, file);
        com.cdhaixun.domain.User userDb=new  com.cdhaixun.domain.User();
        userDb.setImg(upload.getData().toString());
        userDb.setId(user.getId());
        userService.update(userDb);
        result.setResult(true);
        return result;
    }
    @RequestMapping(value = "modifyUser", method = RequestMethod.POST)
    @ResponseBody
    public Result modifyUser(@RequestBody User user) throws Exception {
        Result result = new Result();
        com.cdhaixun.domain.User userDb=new  com.cdhaixun.domain.User();
        ConvertUtils.register(new DateConverter(null), java.util.Date.class);
        ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
        BeanUtils.copyProperties(user,userDb);
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
        if(captcha!=null&&captcha.getCaptcha().equals(mobile.getCaptcha())&&(System.currentTimeMillis()-captcha.getCreateTime().getTime())<3*60*1000) {
            userDb.setMobile(mobile.getMobile());
            userService.update(userDb);
            result.setResult(true);
        }else{
            result.setCode(Code.CAPTCHA_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "getUser", method = RequestMethod.POST)
    @ResponseBody
    public Result getUser(@RequestBody User user) throws Exception {
        Result result = new Result();
        com.cdhaixun.domain.User byMobile = userService.findById(user.getId());
        List<Baby> babyList = babyService.findByUserId(user.getId());
        List<Car> carList = carService.findByUserId(user.getId());
        byMobile.setBabyList(babyList);
        byMobile.setCarList(carList);
        result.setResult(true);
        result.setData(byMobile);
        return result;
    }


}
