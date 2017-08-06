package com.cdhaixun.shop.web;

import com.cdhaixun.common.appVo.LonAndLat;
import com.cdhaixun.common.appVo.Result;
import com.cdhaixun.domain.*;
import com.cdhaixun.shop.service.IPotionCategoryService;
import com.cdhaixun.shop.service.IPotionService;
import com.cdhaixun.shop.service.IStorePotionService;
import com.cdhaixun.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jws.Oneway;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Action;
import java.util.*;

@Controller
@RequestMapping("potionCategoryApp")
public class PotionCategoryAppController {
    @Autowired
    private IPotionCategoryService potionCategoryService;
    @Autowired
    private IStorePotionService storePotionService;
    @Autowired
    private IPotionService potionService;

    @RequestMapping(value = "listByStoreId", method = RequestMethod.POST)
    @ResponseBody
    public Result listByStoreId(@RequestBody com.cdhaixun.common.appVo.Store store, HttpServletRequest httpServletRequest ) {
        // Page<Manager> page = PageHelper.startPage(pageNum, pageSize ,true);
        Result result = new Result();
        List<StorePotion> storePotionList=  storePotionService.findByStoreId( store.getStoreid());
     Map<Integer,PotionCategory> map=new HashMap<>();
        for (StorePotion storePotion:storePotionList) {
          Potion potion=  potionService.findById(storePotion.getPotionid());
            if(map.keySet().contains(potion.getPotioncategoryid())){
                PotionCategory potionCategory = map.get(potion.getPotioncategoryid());
                potionCategory.getPotionList().add(potion);
            }
            else{
          PotionCategory potionCategory=  potionCategoryService.findById(potion.getPotioncategoryid());
          map.put(potionCategory.getId(),potionCategory);
          potionCategory.getPotionList().add(potion);
            }

        }
        result.setData(map.values());
        result.setResult(true);
        return result;
    }

}
