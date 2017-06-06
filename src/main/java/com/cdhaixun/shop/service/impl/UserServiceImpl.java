
package com.cdhaixun.shop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.cdhaixun.persistence.UserInfoMapper;
import com.cdhaixun.shop.service.IUserService;
import org.springframework.stereotype.Service;

import com.cdhaixun.domain.UserInfo;

/**
* 作者 lingco
* 日期 2016年10月29日 下午4:46:14
*/
@Service(value="userService")
public class UserServiceImpl implements IUserService {

	@Resource(name="userInfoMapper")
    UserInfoMapper userInfoMapper;
	@Override
	public UserInfo findById(Integer id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public void save(UserInfo domain) throws Exception {
		userInfoMapper.insert(domain);
	}

	@Override
	public void update(UserInfo domain) throws Exception {
		userInfoMapper.updateByPrimaryKey(domain);
	}

	@Override
	public void delete(UserInfo domain) throws Exception {
		userInfoMapper.deleteByPrimaryKey(domain.getId());
	}

	@Override
	public List<UserInfo> selectAll() {
		return userInfoMapper.selectAll();
	}

}
