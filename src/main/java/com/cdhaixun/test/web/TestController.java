package com.cdhaixun.test.web;

import com.cdhaixun.common.util.PageData;
import com.cdhaixun.common.util.Pager;
import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.domain.Menu;
import com.cdhaixun.test.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用于测试的控制器，后期会删除
 * @author tanggm
 *
 */
@Controller
@RequestMapping(value="/test")
public class TestController extends BaseController{

	private static final String PATH = "test/";
	@Autowired
	private ITestService testService;
	/**
	 * 测试菜单跳转
	 * @return
	 */
	@RequestMapping(value="/temp")
	public String formElements(){		
		return PATH + "form_elements";
	}
	@RequestMapping(value="/add")
	public String add(){		
		return PATH + "add";
	}
	@RequestMapping(value="/list")
	public String list(){
		return PATH + "list";
	}
	@RequestMapping(value="/testpage")
	public String pagerTest(){
		return PATH + "pagerTest";
	}
	@RequestMapping(value="/testPager")
	@ResponseBody
	public Object testPager(Pager pager){
		PageData pd = new PageData();
		List<Menu> menus = testService.getMenuList(pager);
		pd.setData(menus);
		pd.setTotals(pager.gettSize());
		pd.setStatus("success");
		//return new PageData(pager.gettSize(), "succes", menus);
		return pd;
	}
}
