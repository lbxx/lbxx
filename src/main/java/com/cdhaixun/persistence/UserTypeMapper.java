package com.cdhaixun.persistence;

import com.cdhaixun.common.persistence.BaseMapper;
import com.cdhaixun.domain.UserType;

import java.util.List;

public interface UserTypeMapper extends BaseMapper<UserType>{
    List<UserType> selectTypeList();
}