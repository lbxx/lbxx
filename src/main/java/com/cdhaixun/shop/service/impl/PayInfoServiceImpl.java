package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.PayInfo;
import com.cdhaixun.persistence.PayInfoMapper;
import com.cdhaixun.shop.service.IPayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017-08-13.
 */
@Service
public class PayInfoServiceImpl implements IPayInfoService {
    @Autowired
    PayInfoMapper payInfoMapper;

    @Override
    public PayInfo findByTrxid(String trxid) {
        PayInfo payInfo=new PayInfo();
        payInfo.setTrxid(trxid);
        PayInfo payInfoDb= payInfoMapper.selectOneByPayInfo(payInfo);
        return payInfoDb;
    }
}
