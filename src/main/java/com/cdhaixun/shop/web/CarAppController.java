package com.cdhaixun.shop.web;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.domain.Car;
import com.cdhaixun.shop.service.ICarService;

/**
 * Created by Administrator on 2017-07-01.
 */
@Controller
@RequestMapping("carApp")
public class CarAppController extends BaseController {
    @Autowired
    private ICarService carService;

    @RequestMapping(value = "addCar", method = RequestMethod.POST)
    @ResponseBody
    public Result addBady(@RequestBody Car car , HttpServletRequest httpServletRequest) throws InvocationTargetException, IllegalAccessException {
        Result result = new Result();
        com.cdhaixun.domain.Car carDo=new com.cdhaixun.domain.Car();
        carDo.setUserid(car.getUserid());
        carDo.setId(car.getId());
        carDo.setName(car.getName());
        carDo.setMileage(car.getMileage());
        carDo.setRemark(car.getRemark());
        carDo.setPlatenumber(car.getPlatenumber());
        carService.save(carDo);
        result.setData(carDo);
        result.setResult(true);
        return result;
    }
    @RequestMapping(value = "deleteCar", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteBady(@RequestBody Car car , HttpServletRequest httpServletRequest) throws InvocationTargetException, IllegalAccessException {
        Result result = new Result();
        com.cdhaixun.domain.Car carDo=new com.cdhaixun.domain.Car();
        //将APP端传入的参数设置给carDo
        carDo.setId(car.getId());
        carService.delete(carDo);
        result.setResult(true);
        return result;
    }


}
