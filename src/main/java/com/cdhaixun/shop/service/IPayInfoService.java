package com.cdhaixun.shop.service;

import com.cdhaixun.domain.PayInfo;

/**
 * Created by Administrator on 2017-08-13.
 */
public interface IPayInfoService {
    PayInfo findByTrxid(String trxid);
}
