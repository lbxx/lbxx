package com.cdhaixun.shop.service;

import com.cdhaixun.common.service.BaseService;
import com.cdhaixun.domain.KnowledgeType;

import java.util.List;
import java.util.Map;

/**
 * @Author tanggm
 * @Date 2017/6/14 23:13
 */
public interface IKnowledgeTypeService extends BaseService<KnowledgeType> {

    List<KnowledgeType> getTypeList(Map<String, Object> parMap);

    /**
     * 查询列表
     * @return
     */
    List<KnowledgeType> findList();
}
