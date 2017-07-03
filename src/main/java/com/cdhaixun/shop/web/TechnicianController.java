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
import com.cdhaixun.domain.Store;
import com.cdhaixun.domain.Technician;
import com.cdhaixun.domain.User;
import com.cdhaixun.shop.service.IBusinessService;
import com.cdhaixun.shop.service.IStoreService;
import com.cdhaixun.shop.service.ITechnicianBusinessService;
import com.cdhaixun.shop.service.ITechnicianService;
import com.cdhaixun.util.MapUtils;
import com.cdhaixun.util.Pager;
@Controller
@RequestMapping("/technician")
public class TechnicianController extends BaseController {
    private static final String PATH = "technician/";
    
    @Autowired
    ITechnicianService techinicianService;
    @Autowired
    IBusinessService businessService;
    @Autowired
    ITechnicianBusinessService technicianBusinessService;
    @Autowired
    IStoreService storeService;
    
    /**
     * 技师首页
     * @return
     */
    @RequestMapping(value="/listIndex")
    public String list(){
        return PATH + "technicianList";
    }
    /**
     * 跳转到添加技师页面
     * @return
     */
    @RequestMapping(value="/addIndex")
    public String add(){
        return PATH + "technicianAdd";
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
    
    /**
     * 跳转添加技师
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(HttpServletRequest request){
        return PATH + "technicianAdd";
    }
    /**
     * 编辑技师跳转
     * @param request
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(HttpServletRequest request){
        return PATH + "technicianAdd";
    }

    /**
     * 提交
     * @param
     * @return
     */
    @SuppressWarnings("unused")
    @RequestMapping(value = "/save")
    @ResponseBody
    public Result save( Technician technician,HttpServletRequest request){
        @SuppressWarnings("unused")
        Map<String, Object> parMap = MapUtils.getParamMap(request);
        return techinicianService.save(technician,parMap);
    }

    /**
     * 删除
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public Object remove(User user){
        try{
        }catch(Exception e){
            e.printStackTrace();
            return "删除失败";
        }
        return "删除成功";
    }
    
    @RequestMapping(value="businessInfo",method=RequestMethod.GET)
    @ResponseBody
    public List<Business> getBusinessInfoByStoreId(HttpServletRequest request){
        int storeId = Integer.parseInt(request.getParameter("storeId"));
        return businessService.findByStoreId(storeId);
    }
    
    @RequestMapping(value="technicianInfo",method=RequestMethod.GET)
    @ResponseBody
    public List<Object> selectByPrimaryKey(HttpServletRequest request){
        List<Object> technicianInfo = new ArrayList<Object>();
        int technicianid = Integer.parseInt(request.getParameter("id"));
        Technician technician = techinicianService.selectByPrimaryKey(technicianid);
         technicianInfo.add(technician);
         technicianInfo.add(technicianBusinessService.findByTechnicianId(technicianid));
         Store store = storeService.findById(technician.getStoreid());
         technicianInfo.add(store);
         return technicianInfo;
    }
    
}
