package com.cdhaixun.persistence;

import java.util.List;

import com.cdhaixun.domain.Business;

public interface BusinessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Business record);

    int insertSelective(Business record);
    /**
     * @param businessId
     * @return
     * 根据业务id获取该业务的信息
     */
    Business selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Business record);

    int updateByPrimaryKey(Business record);

    /**
     * @return
     * 列出所有的业务
     */
    List<Business> listBusiness();

    List<Business> selectByBusiness(Business business);

    /**
     * @param storeId
     * @return
     * 根据店铺id获取该店铺开启的所有业务
     */
    List<Business> getBusinessInfoByStoreId(Integer storeId);

    void deleteByBusinessId(int businessId);
    
}