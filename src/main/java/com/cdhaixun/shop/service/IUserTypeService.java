package com.cdhaixun.shop.service;

import com.cdhaixun.common.service.BaseService;
import com.cdhaixun.domain.ChainStore;
import com.cdhaixun.domain.UserType;
import com.cdhaixun.util.Pager;

import java.util.List;
import java.util.Map;

/**
 * @Author tanggm
 * @Date 2017/6/14 23:13
 */
public interface IUserTypeService extends BaseService<UserType> {
    /**
     * 查询用户类型列表
     * @param pager
     * @param parMap
     * @return
     */
     Pager getUserTypeList(Pager pager, Map<String, Object> parMap);

    /**
     * 查询连锁店id
     * @return
     */
    List<ChainStore> getChainStoreList();
}
