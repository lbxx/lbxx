package com.cdhaixun.persistence;

import com.cdhaixun.common.persistence.BaseMapper;
import com.cdhaixun.domain.ChainStore;
import com.cdhaixun.domain.UserType;
import com.cdhaixun.vo.UserTypeListVo;

import java.util.List;
import java.util.Map;

public interface UserTypeMapper extends BaseMapper<UserType>{
    List<UserType> selectTypeList();

    List<UserTypeListVo> findUserTypeList(Map<String, Object> parMap);

    List<ChainStore> getChainStoreList();
}