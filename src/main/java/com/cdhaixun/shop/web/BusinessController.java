package com.cdhaixun.shop.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdhaixun.common.vo.Result;
import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.domain.Business;
import com.cdhaixun.domain.Category;
import com.cdhaixun.shop.service.IBusinessService;
import com.cdhaixun.shop.service.ICategoryService;
import com.cdhaixun.util.MapUtils;
import com.cdhaixun.util.Pager;

/**
 * @author DengQi
 * @date		2017年7月7日下午3:18:30
 * 业务管理
 */
@Controller
@RequestMapping("/business")
public class BusinessController extends BaseController{
    private static final String PATH = "business/";
    
    @Autowired
    IBusinessService businessService;
    @Autowired
    ICategoryService categoryService;
    
    @RequestMapping(value="/listIndex")
    public String Index(){
        return PATH+"businessList";
    }
    
    /**
     * 跳转添加技师
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(HttpServletRequest request){
        return PATH + "businessAdd";
    }
    
    /**
     * @param pager
     * @param request
     * @return
     * 列出所有业务
     */
    @RequestMapping(value="/listgrid",method = RequestMethod.GET)
    @ResponseBody
    public Pager listgrid(Pager pager, HttpServletRequest request){
        try{
            Map<String, Object> parMap = MapUtils.getParamMapObj(request);
            Pager resPager =  businessService.selectBusinessList(pager, parMap);
            return resPager;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 提交
     * @param
     * @return
     */
    @SuppressWarnings("unused")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Result save( Business business,HttpServletRequest request){
        @SuppressWarnings("unused")
        Map<String, Object> parMap = MapUtils.getParamMap(request);
        return businessService.save(business,parMap);
    }

    /**
     * 删除
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public Object remove(HttpServletRequest request){
        Result result = new Result();
        try{
            int businessId = Integer.valueOf(request.getParameter("id"));
            businessService.deleteByBusinessId(businessId);//删除技师
        }catch(Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("删除失败");
            return  result;
        }
        result.setResult(true);
        result.setMsg("删除成功");
        return result;
    }
    
    /**
     * @param request
     * @return
     * 修改时根据businessId查询相关business的信息及所属分类
     */
    @RequestMapping(value="/businessInfo",method=RequestMethod.GET)
    @ResponseBody
    public Business getBusinessInfoByBusinessId(HttpServletRequest request){
//        List list = new ArrayList<>();
        int businessId = Integer.parseInt(request.getParameter("businessid"));
        Business business =  businessService.getBusinessInfoByBusinessId(businessId);
        return business;
    }
    
    /**
     * @param request
     * @return
     * 查询所有分类
     */
    @RequestMapping(value="/category",method=RequestMethod.GET)
    @ResponseBody
    public List<Category> getCategorys(HttpServletRequest request){
        return categoryService.findAll();
    }
}
