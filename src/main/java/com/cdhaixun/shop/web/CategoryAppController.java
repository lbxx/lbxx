package com.cdhaixun.shop.web;

import com.cdhaixun.common.appVo.LonAndLat;
import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.domain.Business;
import com.cdhaixun.domain.Category;
import com.cdhaixun.domain.Manager;
import com.cdhaixun.domain.Store;
import com.cdhaixun.shop.service.IBusinessService;
import com.cdhaixun.shop.service.ICategoryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public Result list(HttpServletRequest httpServletRequest) {
        Result result = new Result();
        List<Category> categoryList = categoryService.findAll();
        for (Category category : categoryList) {
            List<Business> businessList = businessService.findByCategoryId(category.getId());
            Map<Integer, Business> businessMap = new HashMap<Integer, Business>();
            for (Business business : businessList) {
                businessMap.put(business.getId(), business);
            }
            category.setBusinessList((List<Business>) businessMap.values());

        }
        result.setResult(true);
        return result;
    }

}
