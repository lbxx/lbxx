package com.cdhaixun.shop.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cdhaixun.common.constant.SessionConstant;
import com.cdhaixun.domain.Business;
import com.cdhaixun.domain.ChainStore;
import com.cdhaixun.domain.Store;
import com.cdhaixun.domain.StoreBusiness;
import com.cdhaixun.persistence.BusinessMapper;
import com.cdhaixun.persistence.ChainStoreMapper;
import com.cdhaixun.persistence.StoreBusinessMapper;
import com.cdhaixun.persistence.StoreMapper;
import com.cdhaixun.shop.service.IStoreService;
import com.cdhaixun.util.Pager;
import com.cdhaixun.util.UploadImages;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class StoreServiceImpl implements IStoreService {
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private ChainStoreMapper chainStoreMapper;
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private StoreBusinessMapper storeBusinessMapper;

    @Value("#{configProperties['imgStore']}")
    private String imgStore;

    @Override
    public void updateStoreBusiness(Integer storeId, String[] businessArr) {
        storeBusinessMapper.deleteByStoreId(storeId);
        Date date = new Date();
        for(String businessId : businessArr){
            StoreBusiness storeBusiness = new StoreBusiness();
            storeBusiness.setBusinessid(Integer.parseInt(businessId));
            storeBusiness.setStoreid(storeId);
            storeBusiness.setCreatetime(date);
            storeBusinessMapper.insertStoreBusinessSelective(storeBusiness);
        }
    }
    public void updateStoreBusinessTs(Integer storeId, String[] businessArr) {
        storeBusinessMapper.deleteByStoreId(storeId);
        Date date = new Date();
        for(String businessId : businessArr){
            StoreBusiness storeBusiness = new StoreBusiness();
            storeBusiness.setBusinessid(Integer.parseInt(businessId));
            storeBusiness.setStoreid(storeId);
            storeBusiness.setCreatetime(date);
            storeBusinessMapper.insertStoreBusinessSelective(storeBusiness);
        }
    }
    @Override
    public List<Store> listAllStores() {
        return storeMapper.listAllStores();         
    }
    public List<ChainStore> listChainStores(){
        return chainStoreMapper.listChainStores();
    }
    @Override
    public Pager getStoreList(Pager pager, String name) {
        Map<String, Object> parMap = new HashMap<>();
        parMap.put("name", name);
        Page dbpage = PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<Store> stores = storeMapper.listAllStores();
        pager.setTotal(dbpage.getTotal());
        pager.setResult(stores);
        pager.setPages(dbpage.getPages());
        
        return pager;
    }
    /*
     * 根据id删除store
     */
    @Override
    public int deleteStoreById(int id) {
        return storeMapper.deleteByPrimaryKey(id);
    }
    public int updateIsDeleteById(int id){
        return storeMapper.updateIsDeleteById(id);
    }
    @Override
    public int updateByPrimaryKeySelective(Store store,String[] businessArr) {       
        
        storeMapper.updateByPrimaryKeySelective(store);
        if(businessArr != null && businessArr.length > 0){
            updateStoreBusinessTs(store.getId(),businessArr);
           }
        return 0;
    }
    @Override
    public List<Store> selectByPrimaryKey(int id) {
        return storeMapper.selectByPrimaryKey(id);        
    }
    /* (non-Javadoc)
     * 获取已有的业务
     */
    @Override
    public List<Business> listBusiness() {
        return businessMapper.listBusiness();
    }
    @Override
    public int insertSelective(Store store, MultipartFile file, HttpServletRequest request) {
        if(file != null){
            String picPath = uploadPic(request,file);
            store.setPic(picPath);
        }
        Date date = new Date();
//        java.sql.Timestamp timeStamp = new java.sql.Timestamp(date.getTime());
        store.setCreatetime(date);
        storeMapper.insertSelective(store);
        return store.getId();
    }
    
    private String uploadPic(HttpServletRequest request,MultipartFile file) {
        String savePath = "";
//        String path1 = request.getSession().getServletContext().getRealPath("/");
        InputStream inStream = StoreServiceImpl.class.getClassLoader().getResourceAsStream("system.properties");  
        Properties prop = new Properties();  
        try {
            prop.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }  
        String path1 = prop.getProperty("imgRoot");  
        String path2 =imgStore;
        savePath = UploadImages.upLoadImage(request, file, path1, path2);  
         return savePath;
     }
    @Override
    public void insertStoreBusiness(int storeid, String[] businessArr) {
        System.out.println(111);
        Date date = new Date();
//        java.sql.Timestamp timeStamp = new java.sql.Timestamp(date.getTime());
        for(String businessId : businessArr){
            StoreBusiness storeBusiness = new StoreBusiness();
            storeBusiness.setBusinessid(Integer.parseInt(businessId));
            storeBusiness.setStoreid(storeid);
            storeBusiness.setCreatetime(date);
            storeBusinessMapper.insertStoreBusinessSelective(storeBusiness);
        }
    }
    @Override
    public List<Integer> getStoreBusinessByStoreId(int storeId) {
        List<Integer> businessId = new ArrayList<>();
        List<StoreBusiness> list = storeBusinessMapper.getStoreBusinessByStoreId(storeId);
        for(StoreBusiness storeBusiness : list){
            businessId.add(storeBusiness.getBusinessid());
        }
        return businessId;
    }
    @Override
    public void updateByPrimaryKeySelective(Store store, MultipartFile file, HttpServletRequest request) {
        if(file != null){
            String picPath = uploadPic(request,file);
            store.setPic(picPath);
        }
        Date date = new Date();
        store.setCreatetime(date);
        storeMapper.updateByPrimaryKeySelective(store);
    }
    
}
