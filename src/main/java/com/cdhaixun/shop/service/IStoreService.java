package com.cdhaixun.shop.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.cdhaixun.domain.Business;
import com.cdhaixun.domain.ChainStore;
import com.cdhaixun.domain.Store;
import com.cdhaixun.domain.StoreBusiness;
import com.cdhaixun.util.Pager;

public interface IStoreService {
    List<Store> listAllStores();
    List<ChainStore> listChainStores();
    Pager getStoreList(Pager pager, String name);
    int deleteStoreById(int id);
    int updateByPrimaryKeySelective(Store store,String[] businessArr);
    List<Store> selectByPrimaryKey(int id);
    List<Business> listBusiness();
    int insertSelective(Store store,MultipartFile file,HttpServletRequest request) throws IOException;
    void insertStoreBusiness(int storeid, String[] businessArr);      
    List<Integer> getStoreBusinessByStoreId(int storeId);
    int updateIsDeleteById(int id);
    void updateByPrimaryKeySelective(Store store, MultipartFile file, HttpServletRequest request);
    void updateStoreBusiness(Integer id, String[] businessArr);

    /**
     * 根据对象查询list
     * @param store
     * @return
     */
    List<Store> find(Store store);

    Store findById(Integer storeid);
}
