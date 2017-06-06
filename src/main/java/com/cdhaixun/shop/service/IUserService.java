package com.cdhaixun.shop.service;

import java.util.List;

import com.cdhaixun.common.service.BaseService;
import com.cdhaixun.domain.UserInfo;

/**
* 作者 lingco
* 日期 2016年10月29日 下午4:43:10
*/
public interface IUserService extends BaseService<UserInfo>{

	/**
	 * 查询所有的用户列表
	 * @return
	 */
	List<UserInfo> selectAll();

}
