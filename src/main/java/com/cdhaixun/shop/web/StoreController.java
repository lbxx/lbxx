package com.cdhaixun.shop.web;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdhaixun.common.vo.Result;
import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.domain.ChainStore;
import com.cdhaixun.domain.Menu;
import com.cdhaixun.domain.Store;
import com.cdhaixun.shop.service.IStoreService;
import com.cdhaixun.util.Pager;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mysql.fabric.xmlrpc.base.Array;

/**
 * @author DengQi
 * @date		2017年6月13日下午1:56:18
 */
@Controller
@RequestMapping("/store")
public class StoreController extends BaseController {
    private static final String PATH = "store/";
    @Autowired
    public IStoreService storeService;
    
    @SuppressWarnings("unused")
    @RequestMapping(value="/index")
    public String index(){
        return PATH+"storelist";
    }
    
    @RequestMapping(value="/add")
    public String add(){
        return PATH+"storeadd";
    }
    
    @RequestMapping(value="/option")
    @ResponseBody
    public List option(){
        HashMap<String,List> storeMap = new HashMap<>();
        List storeInfo = new ArrayList<>();
        List<ChainStore> chainStores = storeService.listChainStores();
        List<Store> stores  = storeService.listAllStores();
        for(ChainStore chainStore :chainStores){
            List<Store> branchStores = new ArrayList<>();
            for(Store store :stores){
                if(store.getChainstoreid() != null && chainStore.getId() == store.getChainstoreid()){
                    branchStores.add(store);
                }
            }
            storeMap.put(chainStore.getId().toString(), branchStores);
        }
        storeInfo.add(chainStores);
        storeInfo.add(storeMap);
        return  storeInfo;
    }
    
    @RequestMapping(value="/list")
    @ResponseBody
    public Object list(Pager pager,String name){
        Pager stores = storeService.getStoreList(pager, name); 
        return stores;
    }
    
    @RequestMapping(value="/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteStore(HttpServletRequest request){
        Result result = new Result();
        @SuppressWarnings("unused")
        String oper = request.getParameter("oper");
        String ids = request.getParameter("id");
        String[] idArr = ids.split(",");
        try {
            for(String id : idArr){
                storeService.deleteStoreById(Integer.parseInt(id));
            }
        } catch (Exception e) {
           result.setResult(false);
           result.setMsg("删除店铺失败!");
            e.printStackTrace();
        }
        result.setResult(true);
        result.setMsg("删除店铺成功!");
        return result;
//        return "{'msg':'ok','result':'true'}";
    }
    
    @RequestMapping(value="/update",method = RequestMethod.POST)
    @ResponseBody
    public Result updateStore(Store store){
        Result result = new Result();
        try {
            storeService.updateByPrimaryKeySelective(store);
        } catch (Exception e) {
            result.setResult(false);
            result.setMsg("更新店铺失败!");
        }
        result.setResult(true);
        result.setMsg("更新店铺成功!");
        return result;
    }
    @RequestMapping(value="/query")
    @ResponseBody
    public List<Store> queryStoreById(HttpServletRequest request){
            String id = request.getParameter("id");
            return storeService.selectByPrimaryKey(Integer.parseInt(id));
    }
    
    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ResponseBody
    public Result addStore(Store store){
        Result result = new Result();
        try {
            storeService.updateByPrimaryKeySelective(store);
        } catch (Exception e) {
            result.setResult(false);
            result.setMsg("添加店铺失败!");
        }
        result.setResult(true);
        result.setMsg("添加店铺成功!");
        return result;
    }
}
