package com.cdhaixun.persistence;

import com.cdhaixun.common.persistence.BaseMapper;
import com.cdhaixun.domain.KnowledgeType;

import java.util.List;
import java.util.Map;

public interface KnowledgeTypeMapper extends BaseMapper<KnowledgeType>{
    List<KnowledgeType> getTypeList(Map<String, Object> parMap);

    List<KnowledgeType> findList();
}