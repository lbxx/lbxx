package com.cdhaixun.test.web;

import com.cdhaixun.common.util.PageData;
import com.cdhaixun.common.util.Pager;
import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.domain.Menu;
import com.cdhaixun.test.service.ITestService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.crypto.hash.Hash;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于测试的控制器，后期会删除
 * @author tanggm
 *
 */
@Controller
@RequestMapping(value="/test")
public class TestController extends BaseController {

	private static final String PATH = "test/";
	@Autowired
	private ITestService testService;

	/**
	 * 测试菜单跳转
	 *
	 * @return
	 */
	@RequestMapping(value = "/temp")
	public String formElements() {
		return PATH + "form_elements";
	}

	@RequestMapping(value = "/add")
	public String add() {
		return PATH + "add";
	}

	@RequestMapping(value = "/list")
	public String list() {
		return PATH + "list";
	}
	/**
	 * 分页查询 列表展示  测试
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/testgrid")
	@ResponseBody
	public Object testgrid( HttpServletRequest request) {
		Page dbpage = PageHelper.startPage(Integer.valueOf(request.getParameter("pageNum")), Integer.valueOf(request.getParameter("pageSize")),true);
        List<Menu> menus = testService.getMenuList();
		return dbpage.toPageInfo();
	}
}