package com.cdhaixun.shop.service;

import com.cdhaixun.common.service.BaseService;
import com.cdhaixun.domain.Store;
import com.cdhaixun.domain.User;
import com.cdhaixun.domain.UserType;
import com.cdhaixun.util.Pager;

import java.util.List;
import java.util.Map;

/**
 * @Author tanggm
 * @Date 2017/6/14 23:13
 */
public interface IUserService extends BaseService<User> {
     Pager getUserList(Pager pager, Map<String, Object> parMap);

    List<Store> selectStoreList();

    List<UserType> selectTypeList();

    /**
     * 根据电话查询用户
     * @param mobile
     * @return
     */
    User findByMobile(String mobile);
}
