package com.cdhaixun.shop.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdhaixun.common.vo.Result;
import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.domain.TechnicianLeave;
import com.cdhaixun.shop.service.ITechnicianLeaveService;
import com.cdhaixun.util.MapUtils;
import com.cdhaixun.util.Pager;
@Controller
@RequestMapping("/technicianLeave")
public class TechnicianLeaveController extends BaseController {
    private static final String PATH = "technicianLeave/";
    
    @Autowired
    ITechnicianLeaveService techinicianLeaveService;
  
    
    /**
     * 技师请假首页
     * @return
     */
    @RequestMapping(value="/listIndex")
    public String list(){
        return PATH + "technicianLeaveList";
    }
    /**
     * 跳转到添加技师页面
     * @return
     */
    @RequestMapping(value="/addIndex")
    public String add(){
        return PATH + "technicianLeaveAdd";
    }
    
    @RequestMapping(value="/listgrid",method = RequestMethod.GET)
    @ResponseBody
    public Pager listgrid(Pager pager, HttpServletRequest request){
        try{
            Map<String, Object> parMap = MapUtils.getParamMapObj(request);
            Pager resPager =  techinicianLeaveService.selectTechnicianLeaveList(pager, parMap);
            return resPager;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 跳转添加请假技师
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(HttpServletRequest request){
        return PATH + "technicianLeaveAdd";
    }
    /**
     * 编辑技师请假跳转
     * @param request
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(HttpServletRequest request){
        return PATH + "technicianLeaveAdd";
    }

    /**
     * 提交
     * @param
     * @return
     */
    @SuppressWarnings("unused")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Result save( TechnicianLeave technicianLeave,HttpServletRequest request){
        @SuppressWarnings("unused")
        Map<String, Object> parMap = MapUtils.getParamMap(request);
        return techinicianLeaveService.save(technicianLeave,parMap);
    }

    /**
     * 取消请假,得先判断取消的时间是否已经过了请假开始的时间
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public Object remove(HttpServletRequest request){
        Result result = new Result();
        try{
            int technicianId = Integer.valueOf(request.getParameter("id"));
            return techinicianLeaveService.deleteByTechnicianLeaveId(technicianId);//删除技师
        }catch(Exception e){
            e.printStackTrace();
            result.setResult(false);
            result.setMsg(e.getMessage());
            return  result;
        }
    }
    
    @RequestMapping(value="/technicianLeaveInfo",method=RequestMethod.GET)
    @ResponseBody
    public TechnicianLeave getBusinessInfoByStoreId(HttpServletRequest request){
        int technicianLeaveId = Integer.parseInt(request.getParameter("technicianleaveid"));
        return techinicianLeaveService.findByTechnicianLeaveId(technicianLeaveId);
    }
    
   
    
}
