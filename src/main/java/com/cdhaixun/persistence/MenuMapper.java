package com.cdhaixun.persistence;

import java.util.List;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cdhaixun.domain.Menu;
@Repository
public interface MenuMapper {

	List<Menu> getMenus(@Param(value = "role") String role, @Param("parentid") Integer parentid);
	/**
	 * 查询所有菜单，测试分页
	 * @return
	 */
	List<Menu> findAll();
}