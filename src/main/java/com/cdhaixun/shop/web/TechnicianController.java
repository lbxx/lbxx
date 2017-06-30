package com.cdhaixun.shop.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.shop.service.ITechnicianService;
import com.cdhaixun.util.MapUtils;
import com.cdhaixun.util.Pager;
@Controller
@RequestMapping("/technician")
public class TechnicianController extends BaseController {
    private static final String PATH = "technician/";
    
    @Autowired
    ITechnicianService techinicianService;
    
    /**
     * 技师首页
     * @return
     */
    @RequestMapping(value="/listIndex")
    public String list(){
        return PATH + "technician";
    }
    /**
     * 添加技师
     * @return
     */
    @RequestMapping(value="/addIndex")
    public String add(){
        return PATH + "technicianadd";
    }
    
    @RequestMapping(value="/listgrid",method = RequestMethod.GET)
    @ResponseBody
    public Pager listgrid(Pager pager, HttpServletRequest request){
        try{
            Map<String, Object> parMap = MapUtils.getParamMapObj(request);
            Pager resPager =  techinicianService.selectTechnicianList(pager, parMap);
            return resPager;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
