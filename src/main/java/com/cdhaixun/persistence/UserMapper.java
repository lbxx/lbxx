package com.cdhaixun.persistence;

import com.cdhaixun.common.persistence.BaseMapper;
import com.cdhaixun.domain.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User>{
    List<User> findUserList(Map<String, Object> parMap);
}