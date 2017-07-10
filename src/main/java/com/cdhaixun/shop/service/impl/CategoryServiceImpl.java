package com.cdhaixun.shop.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cdhaixun.common.vo.Result;
import com.cdhaixun.domain.Business;
import com.cdhaixun.domain.Category;
import com.cdhaixun.persistence.CategoryMapper;
import com.cdhaixun.shop.service.ICategoryService;
import com.cdhaixun.util.Pager;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Created by Administrator on 2017-07-01.
 */
@Service
public class CategoryServiceImpl implements ICategoryService{
@Autowired
private CategoryMapper categoryMapper;
    @Override
    public List<Category> findAll() {
        return categoryMapper.listCategory();
    }
    @Override
    public Pager selectCategoryList(Pager pager, Map<String, Object> parMap) {
        Page dbpage = PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<Category> list = categoryMapper.listCategory();
        pager.setTotal(dbpage.getTotal());
        pager.setResult(list);
        pager.setPages(dbpage.getPages());
        return pager;
    }
    @Override
    public Result save(Category category, Map<String, Object> parMap) {
        Result result = new Result();
        Date date = new Date();
        category.setCreatetime(date);
      //如果id不为空,表示是更新分类
        if(parMap.get("id") != null && !("".equals(parMap.get("id")))){
            try {
                categoryMapper.updateByPrimaryKeySelective(category);
            } catch (Exception e) {
                e.printStackTrace();
                result.setResult(false);
                result.setMsg("更新分类失败");
                return result;
            }
            result.setResult(true);
            result.setMsg("更新分类成功");
            return result;
        }else{////添加分类
            try {
                categoryMapper.insertSelective(category);
            } catch (Exception e) {
                e.printStackTrace();
                result.setResult(false);
                result.setMsg("添加分类失败");
                return result;
            }
            result.setResult(true);
            result.setMsg("添加分类成功");
            return result;
        }
    }
    @Override
    public void deleteByCategory(int categoryId) {
        categoryMapper.deleteByCategory(categoryId);
    }
    @Override
    public Category getBusinessInfoByCategoryId(int categoryId) {

        return categoryMapper.selectByPrimaryKey(categoryId);
    }
    
}
