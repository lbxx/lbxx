package com.cdhaixun.shop.service;

import java.util.List;

import com.cdhaixun.domain.Car;

public interface ICarService {
    void save(Car carDo);

    List<Car> findByUserId(Integer id);

    void delete(Car carDo);

    Car findById(Integer id);
}
