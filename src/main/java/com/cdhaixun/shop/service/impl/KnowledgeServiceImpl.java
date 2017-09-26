package com.cdhaixun.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdhaixun.domain.Knowledge;
import com.cdhaixun.persistence.KnowledgeMapper;
import com.cdhaixun.shop.service.IKnowledgeService;
import com.cdhaixun.vo.KnowledgeVo;

/**
 * @Author tanggm
 * @Date 2017/6/14 23:33
 */
@Service
public class KnowledgeServiceImpl implements IKnowledgeService {
    @Autowired
    private KnowledgeMapper knowledgeMapper;

    @Override
    public Knowledge findById(Integer id) {
        return knowledgeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(Knowledge domain) throws Exception {
        knowledgeMapper.insert(domain);
    }

    @Override
    public void update(Knowledge domain) throws Exception {
        knowledgeMapper.updateByPrimaryKey(domain);
    }

    @Override
    public void delete(Knowledge domain) throws Exception {
        knowledgeMapper.deleteByPrimaryKey(domain.getId());
    }

    @Override
    public List<KnowledgeVo> getList(Map<String, Object> parMap) {
        return knowledgeMapper.getList(parMap);
    }

    @Override
    public List<Knowledge> findList() {
        return knowledgeMapper.findList();
    }

    @Override
    public List<Knowledge> findByTypeId(Integer typeid) {
        Knowledge knowledge = new Knowledge();
        knowledge.setTypeid(typeid);
        knowledge.setIsdelete(false);
        List<Knowledge> knowledgeList = knowledgeMapper.findByKnowledge(knowledge);

        return knowledgeList;
    }
}
