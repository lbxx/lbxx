package com.cdhaixun.shop.web;

import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.shop.service.IAppointmentDetailService;
import com.cdhaixun.shop.service.IAppointmentService;
import com.cdhaixun.util.MapUtils;
import com.cdhaixun.vo.AppointmentDetailVo;
import com.cdhaixun.vo.AppointmentVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/appointment")
@ApiIgnore
public class AppointmentController extends BaseController{
    
    private static final String PATH = "appointment/";
    @Autowired
    private IAppointmentService appointmentService;
    @Autowired
    private IAppointmentDetailService appointmentDetailService;

    @RequestMapping(value="/index")
    public String index(){
        return PATH + "index";
    }

    /**
     * 首页列表
     * @return
     */
    @RequestMapping(value="/list")
    public String list(){
        return PATH + "appointment_list";
    }
    /**
     * 查询列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    public PageInfo<AppointmentVo> queryList(HttpServletRequest request){
        try{
            Map<String, Object> parMap = MapUtils.getParamMapObj(request);
            Page<AppointmentVo> page = PageHelper.startPage(Integer.valueOf(request.getParameter("pageNum")), Integer.valueOf(request.getParameter("pageSize")), true);
            List<AppointmentVo> list = appointmentService.getAppointmentList(parMap);
            PageInfo<AppointmentVo> pageInfo = page.toPageInfo();
            return pageInfo;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查看预约详情
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(HttpServletRequest request){
        request.setAttribute("id", request.getParameter("id"));
        return PATH + "appointmentDetail_list";
    }
    /**
     * 查询预约详情列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/appointmentDetailList", method = RequestMethod.POST)
    public PageInfo<AppointmentDetailVo> appointmentDetailList(HttpServletRequest request){
        try{
            Map<String, Object> parMap = MapUtils.getParamMapObj(request);
            Page<AppointmentDetailVo> page = PageHelper.startPage(Integer.valueOf(request.getParameter("pageNum")), Integer.valueOf(request.getParameter("pageSize")), true);
            List<AppointmentDetailVo> list = appointmentDetailService.getAppointmentDetailList(parMap);
            PageInfo<AppointmentDetailVo> pageInfo = page.toPageInfo();
            return pageInfo;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
