package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.Baby;
import com.cdhaixun.persistence.BabyMapper;
import com.cdhaixun.shop.service.IBabyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017-07-01.
 */
@Service
public class BabyServiceImpl implements IBabyService {
    @Autowired
    private BabyMapper babyMapper;

    @Override
    public void save(Baby babyDo) {
        if (babyDo.getId() != null) {
            babyMapper.updateByPrimaryKeySelective(babyDo);
        } else {
            babyMapper.insert(babyDo);
        }
    }

    @Override
    public List<Baby> findByUserId(Integer id) {
        Baby baby = new Baby();
        baby.setUserid(id);
        return babyMapper.selectByBaby(baby);
    }

    @Override
    public void delete(Baby babyDo) {
        babyDo.setIsdelete(true);
        babyMapper.updateByPrimaryKeySelective(babyDo);
    }
}
