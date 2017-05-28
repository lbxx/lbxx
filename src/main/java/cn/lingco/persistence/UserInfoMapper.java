package cn.lingco.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.lingco.common.persistence.BaseMapper;
import cn.lingco.domain.UserInfo;
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo>{

	/**
	 * 查询所有的用户列表
	 * @return
	 */
	List<UserInfo> selectAll();
}