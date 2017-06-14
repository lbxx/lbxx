package com.cdhaixun.test.service.impl;

import com.cdhaixun.domain.Menu;
import com.cdhaixun.persistence.MenuMapper;
import com.cdhaixun.test.service.ITestService;
import com.cdhaixun.util.Pager;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试使用
 *
 * @author tanggm
 */
@Service
public class TestServiceImpl implements ITestService{
	@Autowired
	private MenuMapper menuMapper;

	@Override
	public Pager getMenuList(Pager pager, String name) {
		Map<String, Object> parMap = new HashMap<>();
		parMap.put("name", name);
		Page dbpage = PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
		List<Menu> menus = menuMapper.findAll(parMap);
		pager.setTotal(dbpage.getTotal());
		pager.setResult(menus);
		pager.setPages(dbpage.getPages());
		return pager;
	}
}
