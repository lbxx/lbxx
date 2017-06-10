package com.cdhaixun.shop.service;

import java.util.List;

import com.cdhaixun.domain.Menu;

public interface IMenuService {
	/**
	 * 通过角色获取菜单
	 * @param role
	 * @return
	 */
	List<Menu> getMenus(String role);
	
}
