package com.cdhaixun.persistence;

import com.cdhaixun.common.persistence.BaseMapper;
import com.cdhaixun.domain.Knowledge;
import com.cdhaixun.vo.KnowledgeVo;

import java.util.List;
import java.util.Map;

public interface KnowledgeMapper extends BaseMapper<Knowledge>{
    List<KnowledgeVo> getList(Map<String, Object> parMap);

    List<Knowledge> findList();
}