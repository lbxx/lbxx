package com.cdhaixun.test.service.impl;

import java.util.List;

import com.cdhaixun.common.util.PageData;
import com.cdhaixun.common.util.Pager;
import com.cdhaixun.domain.Menu;
import com.cdhaixun.test.service.ITestService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 测试使用
 * @author tanggm
 *
 */

import com.cdhaixun.persistence.MenuMapper;
@Service
public class TestServiceImpl implements ITestService{
	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<Menu> getMenuList(Pager pager) {
		Page dbpage = PageHelper.startPage(pager.getcPage(), pager.getpSize());
		List<Menu> menus = menuMapper.findAll();
		pager.settSize(dbpage.getTotal());
		return menus;
	}
}
