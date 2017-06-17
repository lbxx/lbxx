package com.cdhaixun.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdhaixun.common.persistence.BaseMapper;
import com.cdhaixun.domain.ChainStore;
import com.cdhaixun.domain.Store;

@Repository
public interface StoreMapper extends BaseMapper<Store>{
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
  int updateByPrimaryKeySelective(Store store);
  List<Store> selectByPrimaryKey(int id);
}