package com.cdhaixun.shop.web;

import com.cdhaixun.common.appVo.City;
import com.cdhaixun.common.appVo.LonAndLat;
import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.domain.Business;
import com.cdhaixun.domain.Category;
import com.cdhaixun.domain.Store;
import com.cdhaixun.domain.StoreBusiness;
import com.cdhaixun.shop.service.IBusinessService;
import com.cdhaixun.shop.service.ICategoryService;
import com.cdhaixun.shop.service.IStoreBusinessService;
import com.cdhaixun.shop.service.IStoreService;
import com.cdhaixun.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2017-07-01.
 */
@Controller
@RequestMapping("categoryApp")
public class CategoryAppController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IBusinessService businessService;
    @Autowired
    private IStoreService storeService;
    @Autowired
    private IStoreBusinessService storeBusinessService;
    @Value("#{configProperties['domainName']}")
    private String domainName;

    @RequestMapping(value = "listByLatAntLon", method = RequestMethod.POST)
    @ResponseBody
    public Result listByLatAntLon(@RequestBody LonAndLat lonAndLat, HttpServletRequest httpServletRequest ,@RequestHeader Integer pageNum, @RequestHeader Integer pageSize ) {
       // Page<Manager> page = PageHelper.startPage(pageNum, pageSize ,true);
        Result result = new Result();
        List<Category> categoryList = categoryService.findAll();
        for (Category category : categoryList) {
            category.setPic(domainName+category.getPic());
            List<Business> businessList = businessService.findByCategoryId(category.getId());
            Map<Integer, Business> businessMap = new HashMap<Integer, Business>();
            for (Business business : businessList) {
                businessMap.put(business.getId(), business);
            }
            category.setBusinessList(new ArrayList<Business>());
            for (Business business:businessMap.values() ) {
                category.getBusinessList().add(business);
            }

            //查询入驻商家
            Set<Integer> storeidList = new HashSet<Integer>();
            for (Business business : category.getBusinessList()) {
                List<StoreBusiness> storeBusinessList = storeBusinessService.findByBusinessId(business.getId());
                for (StoreBusiness storeBusiness : storeBusinessList) {
                    storeidList.add(storeBusiness.getStoreid());
                }
            }
            category.setEnterStoreCount(storeidList.size());
            //查询附近商家
            for (Integer storeid : storeidList) {
                Store store = storeService.findById(storeid);
                double distance = MathUtil.getDistance(lonAndLat.getLatitude().doubleValue(), lonAndLat.getLongitude().doubleValue(), store.getLatitude().doubleValue(), store.getLongitude().doubleValue());
                if (distance <= 3000) {
                    category.setNearbyStoreCount(category.getNearbyStoreCount() + 1);
                }
            }

        }
        result.setData(categoryList);
        result.setResult(true);
        return result;
    }

    @RequestMapping(value = "listByCity", method = RequestMethod.POST)
    @ResponseBody
    public Result listByCity(@RequestBody City city, HttpServletRequest httpServletRequest, @RequestHeader(required = false) Integer pageNum,@RequestHeader(required = false) Integer pageSize ) {
       // Page<Manager> page = PageHelper.startPage(pageNum, pageSize ,true);
        Result result = new Result();

        List<Category> categoryList = categoryService.findAll();
        for (Category category : categoryList) {
            category.setPic(domainName+category.getPic());
            List<Business> businessList = businessService.findByCategoryId(category.getId());
            Map<Integer, Business> businessMap = new HashMap<Integer, Business>();
            for (Business business : businessList) {
                businessMap.put(business.getId(), business);
            }
            category.setBusinessList(new ArrayList<Business>());
            for (Business business:businessMap.values() ) {
                category.getBusinessList().add(business);
            }
            //查询入驻商家
            Set<Integer> storeidList = new HashSet<Integer>();
            for (Business business : category.getBusinessList()) {
                List<StoreBusiness> storeBusinessList = storeBusinessService.findByBusinessId(business.getId());
                for (StoreBusiness storeBusiness : storeBusinessList) {
                    storeidList.add(storeBusiness.getStoreid());
                }
            }
            category.setEnterStoreCount(storeidList.size()) ;
            //查询附近商家
            Store store = new Store();
            store.setCity(city.getCity());
            List<Store> stores = storeService.find(store);
            category.setNearbyStoreCount(stores.size());

        }
        result.setData(categoryList);
        result.setResult(true);
        return result;
}

}
