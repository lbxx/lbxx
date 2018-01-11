package com.cdhaixun.persistence;

import java.util.List;

import com.cdhaixun.common.persistence.BaseMapper;
import com.cdhaixun.domain.ChainStore;
import com.cdhaixun.domain.Store;

public interface StoreMapper extends BaseMapper<Store> {
    @Override
    int deleteByPrimaryKey(Integer id);

    @Override
    int insert(Store record);

    @Override
    Store selectByPrimaryKey(Integer id);

    @Override
    int updateByPrimaryKey(Store record);
    /**
     * @return
     * 查询所有店铺
     */
    List<Store> listAllStores();
    /**
     * @return
     * 查询所有连锁店
     */
    List<ChainStore>listChainStores();
    
    int deleteByPrimaryKey(int id);
    int updateIsDeleteById(int id);
    int updateByPrimaryKeySelective(Store store);
    List<Store> selectByPrimaryKey(int id);

    int insertSelective(Store store);

    List<Store> selectByStore(Store store);
}