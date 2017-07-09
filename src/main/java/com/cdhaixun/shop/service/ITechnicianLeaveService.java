package com.cdhaixun.shop.service;

import java.util.Date;
import java.util.Map;

import com.cdhaixun.common.exception.HxException;
import com.cdhaixun.common.vo.Result;
import com.cdhaixun.domain.TechnicianLeave;
import com.cdhaixun.util.Pager;

/**
 * Created by tangxinmao on 2017/7/2.
 */
public interface ITechnicianLeaveService {
    TechnicianLeave findOneByLeaveDay(Date createtime, Integer technicianid);

    Pager selectTechnicianLeaveList(Pager pager, Map<String, Object> parMap);

    Result save(TechnicianLeave technicianLeave, Map<String, Object> parMap);

    Result deleteByTechnicianLeaveId(int technicianId) throws HxException;

    TechnicianLeave findByTechnicianLeaveId(int technicianLeaveId);
}
