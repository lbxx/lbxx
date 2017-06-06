package com.cdhaixun.shop.web;

import javax.annotation.Resource;

import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.shop.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdhaixun.domain.UserInfo;

/**
* 作者 lingco
* 日期 2016年10月29日 下午4:42:05
* 用户
*/
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Resource
    IUserService userService;
	@RequestMapping("/showInfo/{userId}")
	public String showUserInfo(Model model, @PathVariable Integer userId){
		UserInfo userInfo = userService.findById(userId);
		model.addAttribute("userInfo", userInfo);
		return "/user/showInfo";
	}	
}
