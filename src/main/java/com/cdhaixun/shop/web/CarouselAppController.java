package com.cdhaixun.shop.web;

import com.cdhaixun.common.appVo.Knowledge;
import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.domain.Carousel;
import com.cdhaixun.domain.Image;
import com.cdhaixun.shop.service.ICarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-08-13.
 */
@Controller
@RequestMapping("carouselApp")
public class CarouselAppController  extends BaseController{

    @Autowired
    private ICarouselService carouselService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public Result listByType( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Result result = new Result();
       List<Carousel> carouselList= carouselService.findAll();
        result.setData(carouselList);
        result.setResult(true);
        return result;
    }

}
