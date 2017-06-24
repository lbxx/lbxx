package com.cdhaixun.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdhaixun.common.constant.ModelConstant;
import com.cdhaixun.domain.Menu;
import com.cdhaixun.domain.Operate;
import com.cdhaixun.shop.service.IMenuService;
import com.cdhaixun.shop.service.IOperateService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.entity.ContentType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;
import java.util.List;

public class Interceptor implements HandlerInterceptor {

    @Autowired
    private   IMenuService menuService;
    @Autowired
    private IOperateService operateService;

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {


    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object arg2, ModelAndView modelAndView)
            throws Exception {

            String servletPath= httpServletRequest.getServletPath();
            Menu menu= menuService.findOneByUrl(servletPath);
            if(menu!=null){
             modelAndView.addObject(ModelConstant.MENU,menu);
               List<Operate> operateList= operateService.findByMenuId(menu.getId());
               if(CollectionUtils.isNotEmpty(operateList)){
                   modelAndView.addObject(ModelConstant.MENU_CODE,operateList);
               }
            }



    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object arg2) throws Exception {

       return  true;
    }

}
