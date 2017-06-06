package com.cdhaixun;

import java.util.List;

import javax.annotation.Resource;

import com.cdhaixun.shop.service.IUserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import com.cdhaixun.domain.UserInfo;

/**
* 作者 lingco
* 日期 2016年10月29日 下午5:09:16
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml","classpath:spring-mybatis.xml"})
public class TestUser {
	private static final Logger logger = Logger.getLogger(TestUser.class);
	
	@Resource(name="userService")
    IUserService userService;
	
	/**
	 * 查询全部数据
	 */
	@Test
	public void findAll(){
		List<UserInfo> userList = userService.selectAll();
		logger.info(JSON.toJSON(userList));
	}
	/**
	 * 通过id查询数据
	 */
	@Test
	public void findUserById(){
		UserInfo userInfo = userService.findById(1);
		logger.info(JSON.toJSON(userInfo));
	}
	/**
	 * 添加数据
	 */
	@Test
	public void insertUser(){
		UserInfo userInfo = new UserInfo();
		userInfo.setUname("小光");
		userInfo.setUnumber(19);
		try{
			userService.save(userInfo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 删除数据
	 */
	@Test
	public void deleteUser(){
		UserInfo userInfo = new UserInfo();
		userInfo.setId(1);
		try{
			userService.delete(userInfo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 修改数据
	 */
	@Test
	public void updateUser(){
		UserInfo userInfo = new UserInfo();
		userInfo.setUname("lignco");
		userInfo.setId(2);
		try {
			userService.update(userInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
