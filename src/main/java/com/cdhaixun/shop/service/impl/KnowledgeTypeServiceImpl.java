package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.KnowledgeType;
import com.cdhaixun.persistence.KnowledgeTypeMapper;
import com.cdhaixun.shop.service.IKnowledgeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author tanggm
 * @Date 2017/6/14 23:33
 */
@Service
public class KnowledgeTypeServiceImpl implements IKnowledgeTypeService {
    @Autowired
    private KnowledgeTypeMapper knowledgeTypeMapper;
    @Override
    public KnowledgeType findById(Integer id) {
        return knowledgeTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(KnowledgeType domain) throws Exception {
        knowledgeTypeMapper.insert(domain);
    }

    @Override
    public void update(KnowledgeType domain) throws Exception {
        knowledgeTypeMapper.updateByPrimaryKey(domain);
    }

    @Override
    public void delete(KnowledgeType domain) throws Exception {
        knowledgeTypeMapper.deleteByPrimaryKey(domain.getId());
    }

    @Override
    public List<KnowledgeType> getTypeList(Map<String, Object> parMap) {
        return knowledgeTypeMapper.getTypeList(parMap);
    }

    @Override
    public List<KnowledgeType> findList() {
        return knowledgeTypeMapper.findList();
    }
}
