package com.cdhaixun.shop.web;

import com.cdhaixun.common.appVo.Baby;
import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.common.appVo.Store;
import com.cdhaixun.domain.Business;
import com.cdhaixun.domain.StoreBusiness;
import com.cdhaixun.domain.TechnicianBusiness;
import com.cdhaixun.shop.service.IBusinessService;
import com.cdhaixun.shop.service.IStoreBusinessService;
import com.cdhaixun.shop.service.ITechnicianBusinessService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangxinmao on 2017/7/2.
 */
@Controller
@RequestMapping("businessApp")
public class BusinessAppController {
    @Autowired
    private IStoreBusinessService storeBusinessService;
    @Autowired
    private IBusinessService businessService;
    @Autowired
    private ITechnicianBusinessService technicianBusinessService;

    @RequestMapping(value = "listByStoreId", method = RequestMethod.POST)
    @ResponseBody
    public Result listByStoreId(@RequestBody Store store, HttpServletRequest httpServletRequest) throws InvocationTargetException, IllegalAccessException {
        Result result = new Result();
        List<StoreBusiness> storeBusinessList = storeBusinessService.findByStoreId(store.getStoreid());
        List<Business> businessList=new ArrayList<>();
        for (StoreBusiness storeBusiness :storeBusinessList) {
            Business business=      businessService.findById(storeBusiness.getBusinessid());
            businessList.add(business);
        }
        result.setData(businessList);
        result.setResult(true);
        return result;
    }
    @RequestMapping(value = "getTechnicianBusines", method = RequestMethod.POST)
    @ResponseBody
    public Result getTechnicianBusines(@RequestBody TechnicianBusiness technicianBusiness, HttpServletRequest httpServletRequest) throws InvocationTargetException, IllegalAccessException {
        Result result = new Result();
        TechnicianBusiness byBusinessIdAndTechnicianId = technicianBusinessService.findByBusinessIdAndTechnicianId(technicianBusiness.getBusinessid(), technicianBusiness.getBusinessid());
        result.setData(byBusinessIdAndTechnicianId);
        result.setResult(true);
        return result;
    }

}
