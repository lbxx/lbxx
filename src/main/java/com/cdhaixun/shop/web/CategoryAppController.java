package com.cdhaixun.shop.web;

import com.cdhaixun.common.appVo.LonAndLat;
import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.domain.*;
import com.cdhaixun.shop.service.IBusinessService;
import com.cdhaixun.shop.service.ICategoryService;
import com.cdhaixun.shop.service.IStoreBusinessService;
import com.cdhaixun.shop.service.IStoreService;
import com.cdhaixun.util.MathUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.cdhaixun.util.MathUtil.getDistance;

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

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public Result list(@RequestBody LonAndLat lonAndLat, HttpServletRequest httpServletRequest) {
        Result result = new Result();
        List<Category> categoryList = categoryService.findAll();
        for (Category category : categoryList) {
            List<Business> businessList = businessService.findByCategoryId(category.getId());
            Map<Integer, Business> businessMap = new HashMap<Integer, Business>();
            for (Business business : businessList) {
                businessMap.put(business.getId(), business);
            }
            category.setBusinessList((List<Business>) businessMap.values());
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
                if (distance <=1000) {
                    category.setNearbyStoreCount(category.getNearbyStoreCount() + 1);
                }
            }
        }
        result.setResult(true);
        return result;
    }

}
