package com.cdhaixun.shop.service;

import com.cdhaixun.common.service.BaseService;
import com.cdhaixun.domain.Knowledge;
import com.cdhaixun.vo.KnowledgeVo;

import java.util.List;
import java.util.Map;

/**
 * @Author tanggm
 * @Date 2017/6/14 23:13
 */
public interface IKnowledgeService extends BaseService<Knowledge> {

    List<KnowledgeVo> getList(Map<String, Object> parMap);

    List<Knowledge> findList();

    List<Knowledge> findByTypeId(Integer typeid);
}
