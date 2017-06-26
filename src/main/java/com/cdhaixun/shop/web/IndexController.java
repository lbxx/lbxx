package com.cdhaixun.shop.web;


import java.io.IOException;
<<<<<<< HEAD

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

=======
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cdhaixun.common.constant.SessionConstant;
import com.cdhaixun.domain.Manager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdhaixun.domain.Menu;
>>>>>>> branch 'master' of https://github.com/lbxx/lbxx.git
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
        return "index";
    }

    /**
     * 退出登陆
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "logout")
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession) throws IOException {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
         httpServletResponse.sendRedirect("login.jsp");
    }

}
