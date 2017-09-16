package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.PotionCategory;
import com.cdhaixun.persistence.PotionCategoryMapper;
import com.cdhaixun.shop.service.IPotionCategoryService;
import com.cdhaixun.util.Pager;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PotionCategoryServiceImpl implements IPotionCategoryService {
    @Autowired
    private PotionCategoryMapper potionCategoryMapper;

    @Override
    public PotionCategory findById(Integer potioncategoryid) {
        return potionCategoryMapper.selectByPrimaryKey(potioncategoryid);
    }

    @Override
    public Pager getPotionCategoryList(Pager pager, Map<String, Object> parMap) {
        Page dbpage = PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<PotionCategory> potionCategoryList = potionCategoryMapper.findPotionCategoryList(parMap);
        pager.setTotal(dbpage.getTotal());
        pager.setResult(potionCategoryList);
        pager.setPages(dbpage.getPages());
        return pager;
    }

    @Override
    public void save(PotionCategory domain) throws Exception {
        potionCategoryMapper.insert(domain);
    }

    @Override
    public void update(PotionCategory domain) throws Exception {
        potionCategoryMapper.updateByPrimaryKey(domain);
    }

    @Override
    public void delete(PotionCategory domain) throws Exception {
        potionCategoryMapper.deleteByPrimaryKey(domain.getId());
    }

    @Override
    public List<PotionCategory> findAll() {
        PotionCategory potionCategory=new PotionCategory();
        potionCategory.setIsdelete(false);
        return potionCategoryMapper.selectByPotionCategory(potionCategory);
    }
}
