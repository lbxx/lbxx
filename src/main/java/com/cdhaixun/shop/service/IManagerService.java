package com.cdhaixun.shop.service;

import com.cdhaixun.domain.Manager;

import java.util.List;

/**
 * Created by tangxinmao on 2017/6/19.
 */
public interface IManagerService {
    List<Manager> findManager(Manager manager);
}
