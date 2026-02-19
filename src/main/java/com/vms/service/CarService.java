package com.vms.service;

import com.vms.model.Car;

import java.util.List;

public interface CarService {

    void saveCar(Car car);

    Car getCarById(Integer id);

    List<Car> getAllCars();

    void updateCar(Car car);

    void deleteCar(Integer id);
}
