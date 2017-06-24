package com.cdhaixun.shop.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cdhaixun.common.emun.Code;
import com.cdhaixun.common.vo.Result;
import com.cdhaixun.common.web.BaseController;
import com.cdhaixun.domain.Business;
import com.cdhaixun.domain.ChainStore;
import com.cdhaixun.domain.Store;
import com.cdhaixun.shop.service.IStoreService;
import com.cdhaixun.util.Pager;

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
    public String add(HttpServletRequest request){
        
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
                int val = Integer.parseInt(id);
                storeService.updateIsDeleteById(val);
            }
        } catch (Exception e) {
           result.setResult(false);
           result.setMsg("删除店铺失败!");
            e.printStackTrace();
            return result;
        }
        result.setResult(true);
        result.setMsg("删除店铺成功!");
        return result;
//        return "{'msg':'ok','result':'true'}";
    }
    
    @RequestMapping(value="/update",method = RequestMethod.POST)
    @ResponseBody
    public Result updateStore(Store store,@RequestParam(value="file",required=false) MultipartFile file,
            HttpServletRequest request){
        Result result = new Result();
        String[] businessArr = request.getParameterValues("business");
        try {
            storeService.updateByPrimaryKeySelective(store,businessArr);
        } catch (Exception e) {
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("更新店铺失败!");
            return result;
        }
        result.setResult(true);
        result.setMsg("更新店铺成功!");
        result.setCode(Code.OPER_UPDATE);
        return result;
    }
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/query")
    @ResponseBody
    public List queryStoreById(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        return storeService.selectByPrimaryKey(id);
    }
    
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/queryStoreBusiness")
    @ResponseBody
    public List queryStoreBusinessByStoreId(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
            return storeService.getStoreBusinessByStoreId(id);
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
            npe.printStackTrace();
            result.setResult(false);
            result.setMsg("获取店铺id失败!");
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            result.setResult(false);
            result.setMsg("添加店铺失败!");
            return result;
        }
        result.setResult(true);
        result.setMsg("添加店铺成功!");        
        result.setCode(Code.OPER_ADD);
        return result;
    }
    
    

    @RequestMapping(value="businessList")
    @ResponseBody
    public List<Business> businessList(){
        return storeService.listBusiness();
    }
    
}
