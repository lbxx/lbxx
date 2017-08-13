package com.cdhaixun.shop.service.impl;

import com.cdhaixun.common.vo.Result;
import com.cdhaixun.domain.Carousel;
import com.cdhaixun.domain.Category;
import com.cdhaixun.persistence.CarouselMapper;
import com.cdhaixun.shop.service.ICarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-08-13.
 */
@Service
public class CarouselServiceImpl implements ICarouselService {
  @Autowired
  private CarouselMapper carouselMapper;
    @Override
    public Result save(Category category, Map<String, Object> parMap) {
        return null;
    }

    @Override
    public List<Carousel> findAll() {
        Carousel carousel =new Carousel();

        return carouselMapper.selectByCarousel(carousel);
    }
}
