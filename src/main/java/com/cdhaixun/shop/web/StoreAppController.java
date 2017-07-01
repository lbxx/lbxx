package com.cdhaixun.shop.web;

import com.cdhaixun.common.appVo.City;
import com.cdhaixun.common.appVo.LonAndLat;
import com.cdhaixun.common.appVo.Mobile;
import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.domain.Manager;
import com.cdhaixun.domain.Store;
import com.cdhaixun.shop.service.IStoreService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2017-07-01.
 */
@Controller
@RequestMapping("storeApp")
public class StoreAppController extends BaseController {
    @Autowired
    private IStoreService storeService;
    @RequestMapping(value = "listByLatAntLon", method = RequestMethod.POST)
    @ResponseBody
    public Result listByLatAntLon(@RequestBody LonAndLat lonAndLat , HttpServletRequest httpServletRequest) {
        Page<Manager> page = PageHelper.startPage(Integer.valueOf(httpServletRequest.getParameter("pageNum")), Integer.valueOf(httpServletRequest.getParameter("pageSize")), true);
        Result result = new Result();
        Store store=new Store();
        store.setOrderBy("round(6378.138*2*asin(sqrt(pow(sin( ("+lonAndLat.getLatitude()+"*pi()/180-latitude*pi()/180)/2),2)+cos("+lonAndLat.getLatitude()+"*pi()/180)*cos(latitude*pi()/180)* pow(sin( ("+lonAndLat.getLongitude()+"*pi()/180-longitude*pi()/180)/2),2)))*1000) asc" );
        List<Store> storeList= storeService.find(store);
        result.setData(storeList);
        result.setResult(true);
        return result;
    }
    @RequestMapping(value = "listByCity", method = RequestMethod.POST)
    @ResponseBody
    public Result listByCity(@RequestBody City city , HttpServletRequest httpServletRequest) {
        Page<Manager> page = PageHelper.startPage(Integer.valueOf(httpServletRequest.getParameter("pageNum")), Integer.valueOf(httpServletRequest.getParameter("pageSize")), true);
        Result result = new Result();
        Store store=new Store();
        store.setCity(city.getCity());
        List<Store> storeList= storeService.find(store);
        result.setData(storeList);
        result.setResult(true);
        return result;
    }
}
