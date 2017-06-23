package com.cdhaixun.shop.web;

import java.io.File;
import java.security.Timestamp;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.ls.LSInput;

import com.cdhaixun.common.constant.SessionConstant;
import com.cdhaixun.common.vo.Result;
import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.domain.Business;
import com.cdhaixun.domain.ChainStore;
import com.cdhaixun.domain.Menu;
import com.cdhaixun.domain.Store;
import com.cdhaixun.shop.service.IStoreService;
import com.cdhaixun.util.ImagesUtil;
import com.cdhaixun.util.Pager;
import com.cdhaixun.util.RandomUtil;
import com.cdhaixun.util.UploadImages;
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
    
//    Logger logger = LoggerFactory.getLogger(StoreController.class); 
    
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
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/query")
    @ResponseBody
    public List queryStoreById(HttpServletRequest request){
        List list = new ArrayList<>();
        int id = Integer.parseInt(request.getParameter("id"));
        list.add(storeService.selectByPrimaryKey(id));
        list.add(storeService.getStoreBusinessByStoreId(id));
            return list;
    }
    
    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ResponseBody
    public Result addStore(Store store,@RequestParam(value="file",required=false) MultipartFile file,
            HttpServletRequest request){
        Result result = new Result();
        String[] businessArr = request.getParameterValues("business");
        try {
          
          int storeid = storeService.insertSelective(store,file,request);
          if(businessArr != null && businessArr.length > 0){
           storeService.insertStoreBusiness(storeid,businessArr);
          }
        }catch(NullPointerException npe){
            result.setResult(false);
            result.setMsg("获取店铺id失败!");
            return result;
        }catch (Exception e) {
            result.setResult(false);
            result.setMsg("添加店铺失败!");
            return result;
        }
        result.setResult(true);
        result.setMsg("添加店铺成功!");
        return result;
    }
    
    

    @RequestMapping(value="businessList")
    @ResponseBody
    public List<Business> businessList(){
        return storeService.listBusiness();
    }
    
}
