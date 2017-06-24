package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.Operate;
import com.cdhaixun.persistence.OperateMapper;
import com.cdhaixun.shop.service.IOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017-06-24.
 */
@Service
public class OperateServiceImpl implements IOperateService {
    @Autowired
    private OperateMapper operateMapper;
    @Override
    public List<Operate> findByMenuId(Integer id) {
        return operateMapper.selectByMenuId(id);
    }
    @Override
    public Operate findById(Integer operateid) {
        return operateMapper.selectByPrimaryKey(operateid);
    }
}
