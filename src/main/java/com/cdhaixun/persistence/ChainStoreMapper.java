package com.cdhaixun.persistence;

import java.util.List;

import com.cdhaixun.common.persistence.BaseMapper;
import com.cdhaixun.domain.ChainStore;

public interface ChainStoreMapper extends BaseMapper<ChainStore>{
    int deleteByPrimaryKey(Integer id);

    int insert(ChainStore record);

    int insertSelective(ChainStore record);

    ChainStore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChainStore record);

    int updateByPrimaryKey(ChainStore record);
    
    /**
     * @return
     * 查询所有连锁店
     */
    List<ChainStore>listChainStores();
}