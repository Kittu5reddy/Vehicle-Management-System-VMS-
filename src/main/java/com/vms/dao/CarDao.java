package com.vms.dao;

import com.vms.model.Car;

import java.util.List;

public interface CarDao {

    void save(Car car);

    Car findById(Integer id);

    List<Car> findAll();

    void update(Car car);

    void delete(Integer id);
}
