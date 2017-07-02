package com.cdhaixun.shop.web;

import com.cdhaixun.common.appVo.Business;
import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.common.appVo.Store;
import com.cdhaixun.domain.Technician;
import com.cdhaixun.domain.TechnicianBusiness;
import com.cdhaixun.shop.service.ITechnicianBusinessService;
import com.cdhaixun.shop.service.ITechnicianService;
import com.cdhaixun.util.Pager;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by tangxinmao on 2017/7/2.
 */
@Controller
@RequestMapping("technicianApp")
public class TechnicianAppController {
    @Autowired
    ITechnicianService techinicianService;
    @Autowired
    private ITechnicianBusinessService technicianBusinessService;

    @RequestMapping(value="listByStoreId",method = RequestMethod.POST)
    @ResponseBody
    public Result listByStoreId(@RequestBody Store store, HttpServletRequest request){
        Result result = new Result();
        List<Technician> technicianList=techinicianService.findByStoreId(store.getStoreid());
        result.setData(technicianList);
        result.setResult(true);
        return result;
    }

    @RequestMapping(value="listByBusinessIdList",method = RequestMethod.POST)
    @ResponseBody
    public Result listByBusinessIdList(@RequestBody Business business, HttpServletRequest request){
        Result result = new Result();
        List<TechnicianBusiness> technicianList=technicianBusinessService.findByBusinessIdList(business.getBusinessidList());
        CollectionUtils.isNotEmpty(business.getBusinessidList());
        result.setData(technicianList);
        result.setResult(true);
        return result;
    }

}
