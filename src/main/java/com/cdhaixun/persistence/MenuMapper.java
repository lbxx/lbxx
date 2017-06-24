package com.cdhaixun.persistence;

import com.cdhaixun.domain.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MenuMapper {

	List<Menu> getMenus(@Param(value = "role") String role, @Param("parentid") Integer parentid);
	/**
	 * 查询所有菜单，测试分页
	 * @return
	 */
	List<Menu> findAll(Map<String, Object> parMap);

    Menu selectOneByUrl(@Param(value = "url") String servletPath);
}