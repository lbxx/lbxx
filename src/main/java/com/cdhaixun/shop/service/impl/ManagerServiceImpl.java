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
public class ManagerServiceImpl implements IManagerService {
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public List<Manager> findByManager(Manager manager) {
        return managerMapper.selectByManager(manager);
    }

    @Override
    public void save(Manager manager) {
        if (manager.getId() == null) {
            managerMapper.insert(manager);
        } else {
            managerMapper.updateByPrimaryKey(manager);
        }
    }

    @Override
    public Manager findById(Integer id) {
        return managerMapper.selectByPrimaryKey(id);
    }

    @Override
    public Manager findByMobile(String mobile) {
        Manager manager = new Manager();
        manager.setMobile(mobile);
        return managerMapper.selectOneByManager(manager);
    }

    @Override
    public Manager findOneByAccount(String username) {
        Manager manager = new Manager();
        manager.setAccount(username);
        return managerMapper.selectOneByManager(manager);
    }
}
