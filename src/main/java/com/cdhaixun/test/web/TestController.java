package com.cdhaixun.test.web;

import com.cdhaixun.common.util.PageData;
import com.cdhaixun.common.util.Pager;
import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.domain.Menu;
import com.cdhaixun.test.service.ITestService;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

	@RequestMapping(value = "/testpage")
	public String pagerTest() {
		return PATH + "pagerTest";
	}

	@RequestMapping(value = "/testPager")
	@ResponseBody
	public Object testPager(Pager pager) {
		PageData pd = new PageData();
		List<Menu> menus = testService.getMenuList(pager);

		//return new PageData(pager.gettSize(), "succes", menus);
		return pd;
	}

	@RequestMapping(value = "/testgrid")
	@ResponseBody
	public Object testgrid(Pager pager, HttpServletRequest request) {
			int page = Integer.valueOf(request.getParameter("page"));
			int rows = Integer.valueOf(request.getParameter("rows"));
			pager.setpSize(rows);
			pager.setcPage(page);
			List<Menu> menus = testService.getMenuList(pager);
			PageData pd = new PageData();
			pd.setRows(menus);
			pd.setTotal((int)((pager.gettSize()+pager.getpSize()-1) / pager.getpSize()));
			pd.setPage(pager.getcPage());
			pd.setRecords(pager.gettSize());

			return pd;
	}
}