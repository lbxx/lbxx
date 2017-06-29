package com.cdhaixun.shop.web;

import com.cdhaixun.shop.service.IMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
