package com.cdhaixun.shop.service;

import com.cdhaixun.common.service.BaseService;
import com.cdhaixun.domain.User;
import com.cdhaixun.util.Pager;

import java.util.Map;

/**
 * @Author tanggm
 * @Date 2017/6/14 23:13
 */
public interface IUserService extends BaseService<User>{
     Pager getUserList(Pager pager, Map<String, Object> parMap);
}
