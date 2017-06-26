package com.cdhaixun.shop.service;

import com.cdhaixun.domain.Operate;

import java.util.List;

/**
 * Created by Administrator on 2017-06-24.
 */
public interface IOperateService {
    /**
     * 通过menuId所有操作功能
     * @param id
     * @return
     */
    List<Operate> findByMenuId(Integer id);

    /**
     * 根据id查询operateid
     * @param operateid
     * @return
     */
    Operate findById(Integer operateid);
}
