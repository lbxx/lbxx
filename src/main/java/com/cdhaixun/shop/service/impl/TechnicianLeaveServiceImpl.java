package com.cdhaixun.shop.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdhaixun.common.exception.HxException;
import com.cdhaixun.common.vo.Result;
import com.cdhaixun.domain.TechnicianLeave;
import com.cdhaixun.persistence.TechnicianLeaveMapper;
import com.cdhaixun.shop.service.ITechnicianLeaveService;
import com.cdhaixun.util.DateUtil;
import com.cdhaixun.util.Pager;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Created by tangxinmao on 2017/7/2.
 */
@Service
public class TechnicianLeaveServiceImpl implements ITechnicianLeaveService {
    @Autowired
    private TechnicianLeaveMapper technicianLeaveMapper;
    @Override
    public TechnicianLeave findOneByLeaveDay(Date createtime, Integer technicianid) {
        TechnicianLeave technicianLeave=new TechnicianLeave();
//        technicianLeave.setLeaveday(createtime);
        technicianLeave.setTechnicianid(technicianid);
//        return technicianLeaveMapper.selectOneByTechnicianLeave(technicianLeave);
        return null;
    }
    @Override
    public Pager selectTechnicianLeaveList(Pager pager, Map<String, Object> parMap) {
        Page dbpage = PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<TechnicianLeave> list = technicianLeaveMapper.selectTechnicianLeaveList();
        pager.setTotal(dbpage.getTotal());
        pager.setResult(list);
        pager.setPages(dbpage.getPages());
        return pager;
    }
    @Override
    public Result save(TechnicianLeave technicianLeave, Map<String, Object> parMap) {
        Result result = new Result<>();
        String startTimeStr =  parMap.get("starttime").toString();
        String endTimeStr =  parMap.get("endtime").toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = new Date();
        Date endTime = new Date();
        try {
            startTime = sdf.parse(startTimeStr);
            endTime = sdf.parse(endTimeStr);
        } catch (ParseException e2) {
            e2.printStackTrace();
            result.setResult(false);
            result.setMsg("解析时间出错!");
            return result;
        }
        //校验请假开始时间和结束时间是否合理
        try {
            DateUtil.checkTimeLegal(startTime,endTime);
        } catch (HxException e1) {
            e1.printStackTrace();
            result.setResult(false);
            result.setMsg(e1.getMessage());
            return result;
        }
        Date date = new Date();
        technicianLeave.setCreatetime(date);
        technicianLeave.setStarttime(startTime);
        technicianLeave.setEndtime(endTime);
        //如果id不为空,表示是更新请假
        if(parMap.get("id") != null && !("".equals(parMap.get("id")))){
            TechnicianLeave technicianLeaveRes = technicianLeaveMapper.selectByPrimaryKey(
                    Integer.parseInt(parMap.get("id").toString()));
            if(technicianLeave.getStarttime().after(technicianLeaveRes.getStarttime())){
                result.setResult(false);
                result.setMsg("已超过请假开始时间,不能修改此次请假!");
                return result;
            }
            try {
                technicianLeaveMapper.updateByPrimaryKeySelective(technicianLeave);
            } catch (Exception e) {
                e.printStackTrace();
                result.setResult(false);
                result.setMsg("更新技师请假失败");
                return result;
            }
            result.setResult(true);
            result.setMsg("更新技师请假成功");
            return result;
        }else{//添加技师
            try {
                technicianLeaveMapper.insertSelective(technicianLeave);
            } catch (Exception e) {
                e.printStackTrace();
                result.setResult(false);
                result.setMsg("添加技师请假失败");
                return result;
            }
            result.setResult(true);
            result.setMsg("添加技师请假成功");
            return result;
        }
    }
    @Override
    public Result deleteByTechnicianLeaveId(int technicianId) throws HxException {
        Result result = new Result();
        Date date = new Date();
        TechnicianLeave technicianLeaveRes = technicianLeaveMapper.selectByPrimaryKey(technicianId);
        Date startTime = technicianLeaveRes.getStarttime();
        Date endTime = technicianLeaveRes.getEndtime();
        if(date.after(startTime) && date.before(endTime)){
            throw new HxException("请假已经开始并且尚未结束,不能修改此次请假!");
//            result.setMsg("请假已经开始并且尚未结束,不能修改此次请假!");
        }
        try {
            technicianLeaveMapper.deleteByTechnicianLeaveId(technicianId);
        } catch (Exception e) {
            result.setMsg("删除技师请假失败!");
            throw new HxException("删除技师请假失败!");
        }
        result.setResult(true);
        result.setMsg("删除技师请假成功!");
        return result;
    }
    @Override
    public TechnicianLeave findByTechnicianLeaveId(int technicianLeaveId) {
        return technicianLeaveMapper.selectByPrimaryKey(technicianLeaveId);
    }
}
