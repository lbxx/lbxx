package com.cdhaixun.shop.web;

import com.cdhaixun.common.appVo.City;
import com.cdhaixun.common.appVo.LonAndLat;
import com.cdhaixun.common.appVo.Mobile;
import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.domain.Business;
import com.cdhaixun.domain.Manager;
import com.cdhaixun.domain.Store;
import com.cdhaixun.domain.StoreBusiness;
import com.cdhaixun.shop.service.IBusinessService;
import com.cdhaixun.shop.service.IStoreBusinessService;
import com.cdhaixun.shop.service.IStoreService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-07-01.
 */
@Controller
@RequestMapping("storeApp")
public class StoreAppController extends BaseController {
    @Autowired
    private IStoreService storeService;
    @Autowired
    private IStoreBusinessService storeBusinessService;
    @Autowired
    private IBusinessService businessService;

    @Value("#{configProperties['domainName']}")
    private String domainName;

    @RequestMapping(value = "listByLatAntLon", method = RequestMethod.POST)
    @ResponseBody
    public Result listByLatAntLon(@RequestBody LonAndLat lonAndLat, HttpServletRequest httpServletRequest, @RequestHeader Integer pageNum,@RequestHeader Integer pageSize ) {
        Page<Manager> page = PageHelper.startPage(pageNum, pageSize ,true);
        Result result = new Result();
        Store store = new Store();
        store.setOrderBy("round(6378.138*2*asin(sqrt(pow(sin( (" + lonAndLat.getLatitude() + "*pi()/180-latitude*pi()/180)/2),2)+cos(" + lonAndLat.getLatitude() + "*pi()/180)*cos(latitude*pi()/180)* pow(sin( (" + lonAndLat.getLongitude() + "*pi()/180-longitude*pi()/180)/2),2)))*1000) asc");
        List<Store> storeList = storeService.find(store);
        for (Store storetemp : storeList) {
            storetemp.setPic(domainName+storetemp.getPic());
            storetemp.setBusinessList(new ArrayList<Business>());
            List<StoreBusiness> storeBusinessList = storeBusinessService.findByStoreId(storetemp.getId());
            for (StoreBusiness storeBusiness : storeBusinessList) {
                storetemp.getBusinessList().add(businessService.findById(storeBusiness.getBusinessid()));
            }
        }
        result.setData(storeList);
        result.setResult(true);
        return result;
    }
    @RequestMapping(value = "searchByName", method = RequestMethod.POST)
    @ResponseBody
    public Result searchByName(@RequestBody Store store, HttpServletRequest httpServletRequest, @RequestHeader Integer pageNum,@RequestHeader Integer pageSize ) {
        Page<Manager> page = PageHelper.startPage(pageNum, pageSize ,true);
        Result result = new Result();
        List<Store> storeList = storeService.find(store);
        for (Store storetemp : storeList) {
            storetemp.setPic(domainName+storetemp.getPic());
            storetemp.setBusinessList(new ArrayList<Business>());
            List<StoreBusiness> storeBusinessList = storeBusinessService.findByStoreId(storetemp.getId());
            for (StoreBusiness storeBusiness : storeBusinessList) {
                storetemp.getBusinessList().add(businessService.findById(storeBusiness.getBusinessid()));
            }
        }
        result.setData(storeList);
        result.setResult(true);
        return result;
    }

    @RequestMapping(value = "listByCity", method = RequestMethod.POST)
    @ResponseBody
    public Result listByCity(@RequestBody City city, HttpServletRequest httpServletRequest, @RequestHeader Integer pageNum,@RequestHeader Integer pageSize ) {
        Page<Manager> page = PageHelper.startPage(pageNum, pageSize ,true);
        Result result = new Result();
        Store store = new Store();
        store.setCity(city.getCity());
        List<Store> storeList = storeService.find(store);
        for (Store storetemp : storeList) {
            storetemp.setPic(domainName+storetemp.getPic());
            storetemp.setBusinessList(new ArrayList<Business>());
            List<StoreBusiness> storeBusinessList = storeBusinessService.findByStoreId(storetemp.getId());
            for (StoreBusiness storeBusiness : storeBusinessList) {
                storetemp.getBusinessList().add(businessService.findById(storeBusiness.getBusinessid()));
            }
        }
        result.setData(storeList);
        result.setResult(true);
        return result;
    }
}
