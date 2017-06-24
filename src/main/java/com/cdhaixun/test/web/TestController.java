package com.cdhaixun.test.web;

import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.test.service.ITestService;
import com.cdhaixun.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

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
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/testgrid")
	@ResponseBody
	public Object testgrid(Pager pager, String name) {
			Pager menus = testService.getMenuList(pager, name);
			return menus;
	}
	@RequestMapping(value = "/testDate")
	@ResponseBody
	public Object testgrid(Date data) {

		return null;
	}
	@RequestMapping(value = "/tt")
	public String tt(){
		return PATH + "tt";
	}
}