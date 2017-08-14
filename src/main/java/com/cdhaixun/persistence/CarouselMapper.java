package com.cdhaixun.persistence;

import java.util.List;

import com.cdhaixun.domain.Carousel;

public interface CarouselMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Carousel record);

    int insertSelective(Carousel record);

    Carousel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Carousel record);

    int updateByPrimaryKey(Carousel record);

    List<Carousel> selectByCarousel(Carousel carousel);

    List<Carousel> findByImageId(Integer id);
}