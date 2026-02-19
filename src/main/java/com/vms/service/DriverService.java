package com.vms.service;

import com.vms.model.Driver;

import java.util.List;

public interface DriverService {
    void saveDriver(Driver driver);
    Driver getDriverById(Integer id);
    List<Driver> getAllDrivers();
    void updateDriver(Driver driver);
    void deleteDriver(Integer id);
}
