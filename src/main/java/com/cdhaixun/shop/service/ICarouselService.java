package com.cdhaixun.shop.service;

import java.util.List;
import java.util.Map;

import com.cdhaixun.common.service.BaseService;
import com.cdhaixun.common.vo.Result;
import com.cdhaixun.domain.Carousel;
import com.cdhaixun.domain.Category;

public interface ICarouselService extends BaseService<Carousel> {
    Result save(Category category, Map<String, Object> parMap);

    List<Carousel> findAll();
    
    List<Carousel> findByImageId(Integer id);
    
    

}
