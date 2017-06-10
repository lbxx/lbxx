package com.cdhaixun.shop.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cdhaixun.common.constant.SessionConstant;
import com.cdhaixun.domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdhaixun.domain.Menu;
import com.cdhaixun.shop.service.IMenuService;

/**
 * 首页数据
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private IMenuService menuService;

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, HttpSession httpSession) {
        Manager manager = (Manager) httpSession.getAttribute(SessionConstant.MANAGER);
        String role = "supper";
        List<Menu> menuList = menuService.getMenus(role);
        request.getSession().setAttribute("menuList", menuList);
        return "index";
    }

}
