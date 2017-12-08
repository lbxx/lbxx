package com.cdhaixun.shop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdhaixun.domain.Car;
import com.cdhaixun.persistence.CarMapper;
import com.cdhaixun.shop.service.ICarService;

/**
 * Created by Administrator on 2017-07-01.
 */
@Service
public class CarServiceImpl implements ICarService {
    @Autowired
    private CarMapper carMapper;
    @Override
    public void save(Car carDo) {
        if (carDo.getId() != null && carDo.getId()!=0) {
            carMapper.updateByPrimaryKeySelective(carDo);
        } else {
            carDo.setCreatetime(new Date());
            carDo.setIsdelete(false);
            carMapper.insert(carDo);
        }
        
    }

    @Override
    public List<Car> findByUserId(Integer id) {
        Car car = new Car();
        car.setUserid(id);
        return carMapper.selectByCar(car);
    }

    @Override
    public void delete(Car carDo) {
        carDo.setIsdelete(true);
        carMapper.updateByPrimaryKeySelective(carDo);
        
    }

    @Override
    public Car findById(Integer id) {
        // TODO Auto-generated method stub
        return carMapper.selectByPrimaryKey(id);
    }

    
    
}
