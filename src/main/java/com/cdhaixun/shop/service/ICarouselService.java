package com.cdhaixun.shop.service;

import java.util.Map;

import com.cdhaixun.common.vo.Result;
import com.cdhaixun.domain.Category;

public interface ICarouselService {
    Result save(Category category, Map<String, Object> parMap);
}
