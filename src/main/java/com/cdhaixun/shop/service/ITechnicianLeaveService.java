package com.cdhaixun.shop.service;

import com.cdhaixun.domain.TechnicianLeave;

import java.util.Date;

/**
 * Created by tangxinmao on 2017/7/2.
 */
public interface ITechnicianLeaveService {
    TechnicianLeave findOneByLeaveDay(Date createtime);
}
