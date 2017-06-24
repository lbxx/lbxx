package com.cdhaixun.persistence;

import com.cdhaixun.common.persistence.BaseMapper;
import com.cdhaixun.domain.Operate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OperateMapper extends BaseMapper<Operate>{
    List<Operate> selectByMenuId(@Param(value = "menuid") Integer menuid);
}