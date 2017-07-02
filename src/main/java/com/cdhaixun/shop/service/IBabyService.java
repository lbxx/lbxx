package com.cdhaixun.shop.service;

import com.cdhaixun.domain.Baby;

import java.util.List;

/**
 * Created by Administrator on 2017-07-01.
 */
public interface IBabyService {
    void save(Baby babyDo);

    List<Baby> findByUserId(Integer id);

    void delete(Baby babyDo);
}
