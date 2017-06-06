package com.cdhaixun.persistence;

import java.util.List;

import com.cdhaixun.common.persistence.BaseMapper;
import org.springframework.stereotype.Repository;

import com.cdhaixun.domain.UserInfo;
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {

	/**
	 * 查询所有的用户列表
	 * @return
	 */
	List<UserInfo> selectAll();
}