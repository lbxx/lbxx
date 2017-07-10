package com.cdhaixun.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.cdhaixun.common.vo.Result;
import com.cdhaixun.domain.Category;
import com.cdhaixun.util.Pager;

/**
 * Created by Administrator on 2017-07-01.
 */
public interface ICategoryService {
    List<Category> findAll();

    Pager selectCategoryList(Pager pager, Map<String, Object> parMap);

    Result save(Category category, Map<String, Object> parMap);

    void deleteByCategory(int categoryId);

    Category getBusinessInfoByCategoryId(int categoryId);
}
