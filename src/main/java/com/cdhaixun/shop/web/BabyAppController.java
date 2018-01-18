package com.cdhaixun.shop.web;

import com.cdhaixun.common.appVo.Baby;
import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.shop.service.IBabyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017-07-01.
 */
@Controller
@RequestMapping("babyApp")
public class BabyAppController extends BaseController {
    @InitBinder   
    public void initBinder(WebDataBinder binder) {   
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   
        dateFormat.setLenient(true);   
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
    }  
    @Autowired
    private IBabyService babyService;

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "addBady", method = RequestMethod.POST,consumes="application/json;charset=utf-8")
    @ResponseBody
    public Result addBady(@RequestBody Baby baby , HttpServletRequest httpServletRequest) throws InvocationTargetException, IllegalAccessException {
        Result result = new Result();
        com.cdhaixun.domain.Baby babyDo=new com.cdhaixun.domain.Baby();
        babyDo.setUserid(baby.getUserid());
        babyDo.setGender(baby.getGender());
        babyDo.setName(baby.getName());
        babyDo.setBirthday(baby.getBirthday());
        babyDo.setRemark(baby.getRemark());
        babyDo.setId(baby.getBabyid());
        babyService.save(babyDo);
        result.setData(babyDo);
        result.setResult(true);
        return result;
    }
    @RequestMapping(value = "deleteBady", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteBady(@RequestBody Baby baby , HttpServletRequest httpServletRequest) throws InvocationTargetException, IllegalAccessException {
        Result result = new Result();
        com.cdhaixun.domain.Baby babyDo=new com.cdhaixun.domain.Baby();
        //将APP端传入的参数设置给babyDo
        babyDo.setId(baby.getBabyid());
        babyService.delete(babyDo);
        result.setResult(true);
        return result;
    }


}
