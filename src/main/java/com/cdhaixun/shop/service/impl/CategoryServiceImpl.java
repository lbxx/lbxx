package com.cdhaixun.shop.service.impl;

import com.cdhaixun.domain.Category;
import com.cdhaixun.persistence.CategoryMapper;
import com.cdhaixun.shop.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017-07-01.
 */
@Service
public class CategoryServiceImpl implements ICategoryService{
@Autowired
private CategoryMapper categoryMapper;
    @Override
    public List<Category> findAll() {
        return categoryMapper.selectByCategory(new Category());
    }
}
