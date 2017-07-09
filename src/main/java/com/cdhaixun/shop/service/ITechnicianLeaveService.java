package com.cdhaixun.shop.service;

import com.cdhaixun.common.exception.HxException;
import com.cdhaixun.common.vo.Result;
import com.cdhaixun.domain.TechnicianLeave;
import com.cdhaixun.util.Pager;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by tangxinmao on 2017/7/2.
 */
public interface ITechnicianLeaveService {
    List<TechnicianLeave> findOneByLeaveDay(Date createtime, Integer technicianid);

    Pager selectTechnicianLeaveList(Pager pager, Map<String, Object> parMap);

    Result save(TechnicianLeave technicianLeave, Map<String, Object> parMap);

    Result deleteByTechnicianLeaveId(int technicianId) throws HxException;

    TechnicianLeave findByTechnicianLeaveId(int technicianLeaveId);
}
