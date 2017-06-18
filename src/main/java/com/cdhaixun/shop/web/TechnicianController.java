package com.cdhaixun.shop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdhaixun.common.web.BaseController;
@Controller
@RequestMapping("/technician")
public class TechnicianController extends BaseController {
    private static final String PATH = "technician/";
    
    @RequestMapping(value="/index")
    public String index(){
        return PATH + "technician";
    }
}
