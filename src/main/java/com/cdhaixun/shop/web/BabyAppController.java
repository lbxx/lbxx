package com.cdhaixun.shop.web;

import com.cdhaixun.common.appVo.Baby;
import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.shop.service.IBabyService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Administrator on 2017-07-01.
 */
@Controller
@RequestMapping("babyApp")
public class BabyAppController extends BaseController {
    @Autowired
    private IBabyService babyService;

    @RequestMapping(value = "addBady", method = RequestMethod.POST)
    @ResponseBody
    public Result addBady(@RequestBody Baby baby , HttpServletRequest httpServletRequest) throws InvocationTargetException, IllegalAccessException {
        Result result = new Result();
        com.cdhaixun.domain.Baby babyDo=new com.cdhaixun.domain.Baby();
        BeanUtils.copyProperties(babyDo,baby);
        babyService.save(babyDo);
        result.setResult(true);
        return result;
    }


}
