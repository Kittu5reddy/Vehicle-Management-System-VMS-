package com.vms.service.imp;

import com.vms.dao.CarDao;
import com.vms.dao.imp.CarDaoImpl;
import com.vms.model.Car;
import com.vms.service.CarService;

import java.util.List;

public class CarServiceImpl implements CarService {

    private CarDao carDao = new CarDaoImpl();

    public void saveCar(Car car) {
        carDao.save(car);
    }

    public Car getCarById(Integer id) {
        return carDao.findById(id);
    }

    public List<Car> getAllCars() {
        return carDao.findAll();
    }

    public void updateCar(Car car) {
        carDao.update(car);
    }

    public void deleteCar(Integer id) {
        carDao.delete(id);
    }
}
