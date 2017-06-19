package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.Manager;
import com.cdhaixun.persistence.ManagerMapper;
import com.cdhaixun.shop.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tangxinmao on 2017/6/19.
 */
@Service
public class ManagerServiceImpl implements IManagerService{
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public List<Manager> findManager(Manager manager) {
        return managerMapper.selectByManager(manager);
    }
}
