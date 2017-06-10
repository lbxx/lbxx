package com.cdhaixun.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdhaixun.domain.Menu;
import com.cdhaixun.persistence.MenuMapper;
import com.cdhaixun.shop.service.IMenuService;
@Service
public class MenuServiceImpl implements IMenuService {
	@Autowired
	private MenuMapper menuMapper;
	@Override
	public List<Menu> getMenus(String role) {
		// 查询一级菜单		
		List<Menu> oneMenus = menuMapper.getMenus(role,0);
		for (Menu oneMenu : oneMenus) {
			List<Menu> twoMenus = menuMapper.getMenus(role,oneMenu.getId());
			oneMenu.setMenuList(twoMenus);
			for (Menu twoMenu : twoMenus) {
				List<Menu> threeMenus = menuMapper.getMenus(role,twoMenu.getId());
				twoMenu.setMenuList(threeMenus);
			}
		}
		return oneMenus;
	}

}
