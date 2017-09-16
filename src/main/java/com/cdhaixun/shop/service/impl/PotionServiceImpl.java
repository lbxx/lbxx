package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.Potion;
import com.cdhaixun.domain.PotionCategory;
import com.cdhaixun.persistence.PotionCategoryMapper;
import com.cdhaixun.persistence.PotionMapper;
import com.cdhaixun.shop.service.IPotionService;
import com.cdhaixun.util.Pager;
import com.cdhaixun.vo.PotionVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PotionServiceImpl implements IPotionService {
    @Autowired
    private PotionMapper potionMapper;
    @Autowired
    private PotionCategoryMapper potionCategoryMapper;

    @Override
    public Potion findById(Integer potionid) {
        return potionMapper.selectByPrimaryKey(potionid);
    }

    @Override
    public Pager getPotionList(Pager pager, Map<String, Object> parMap) {
        Page dbpage = PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<PotionVo> potionList = potionMapper.findPotionList(parMap);
        pager.setTotal(dbpage.getTotal());
        pager.setResult(potionList);
        pager.setPages(dbpage.getPages());
        return pager;
    }

    @Override
    public List<PotionCategory> selectPotionCategoryList() {
        return potionCategoryMapper.findPotionCategoryList(null);
    }

    @Override
    public void save(Potion domain) throws Exception {
        potionMapper.insert(domain);
    }

    @Override
    public void update(Potion domain) throws Exception {
        potionMapper.updateByPrimaryKey(domain);
    }

    @Override
    public void delete(Potion domain) throws Exception {
        potionMapper.deleteByPrimaryKey(domain.getId());
    }

    @Override
    public List<Potion> findByPotionCategoryId(Integer id) {
        Potion potion = new Potion();
        potion.setPotioncategoryid(id);
        potion.setIsdelete(false);
        return potionMapper.selectByPotionCategory(potion);
    }
}
