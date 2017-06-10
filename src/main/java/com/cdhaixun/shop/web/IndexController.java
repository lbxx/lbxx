package com.cdhaixun.shop.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdhaixun.domain.Menu;
import com.cdhaixun.shop.service.IMenuService;
/**
 * 首页数据
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/index")
public class IndexController {
	@Autowired
	private IMenuService menuService;
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request){ 
		String role = "supper";
		List<Menu> menuList = menuService.getMenus(role);
		request.getSession().setAttribute("menuList", menuList);
		return "index";
	}

}
