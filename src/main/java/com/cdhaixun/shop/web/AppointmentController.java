package com.cdhaixun.shop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdhaixun.common.web.BaseController;
@Controller
@RequestMapping("/appointment")
public class AppointmentController extends BaseController{
    
    private static final String PATH = "appointment/";
    
    @RequestMapping(value="/index")
    public String index(){
        return PATH + "index";
    }
}
