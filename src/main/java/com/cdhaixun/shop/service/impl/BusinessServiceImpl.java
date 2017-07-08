package com.cdhaixun.shop.service.impl;

import com.cdhaixun.common.vo.Result;
import com.cdhaixun.domain.Business;
import com.cdhaixun.domain.Technician;
import com.cdhaixun.persistence.BusinessMapper;
import com.cdhaixun.shop.service.IBusinessService;
import com.cdhaixun.util.Pager;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-07-01.
 */
@Service
public class BusinessServiceImpl implements IBusinessService{
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public List<Business> findByCategoryId(Integer id) {
        Business business=new Business();
        business.setCategoryid(id);
        return businessMapper.selectByBusiness(business);
    }

    @Override
    public Business findById(Integer businessid) {
        return businessMapper.selectByPrimaryKey(businessid);
    }

    @Override
    public List<Business> findByStoreId(Integer storeId) {
        return businessMapper.getBusinessInfoByStoreId(storeId);
    }

    @Override
    public Pager selectBusinessList(Pager pager, Map<String, Object> parMap) {
        Page dbpage = PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<Business> list = businessMapper.listBusiness();
        pager.setTotal(dbpage.getTotal());
        pager.setResult(list);
        pager.setPages(dbpage.getPages());
        return pager;
    }

    @Override
    public Result save(Business business, Map<String, Object> parMap) {
        Result result = new Result();
        Date date = new Date();
        business.setCreatetime(date);
      //如果id不为空,表示是更新技师
        if(parMap.get("id") != null && !("".equals(parMap.get("id")))){
            try {
                businessMapper.updateByPrimaryKeySelective(business);
            } catch (Exception e) {
                e.printStackTrace();
                result.setResult(false);
                result.setMsg("更新业务失败");
                return result;
            }
            result.setResult(true);
            result.setMsg("更新业务成功");
            return result;
        }else{////添加业务
            try {
                businessMapper.insertSelective(business);
            } catch (Exception e) {
                e.printStackTrace();
                result.setResult(false);
                result.setMsg("添加业务失败");
                return result;
            }
            result.setResult(true);
            result.setMsg("添加业务成功");
            return result;
        }
    }

    @Override
    public void deleteByBusinessId(int businessId) {
        businessMapper.deleteByBusinessId(businessId);
    }

    @Override
    public Business getBusinessInfoByBusinessId(int businessId) {
        return businessMapper.selectByPrimaryKey(businessId);
    }
}
