package com.vms.dao;

import com.vms.model.Driver;

import java.util.List;

public interface DriverDao {

    void save(Driver driver);

    Driver findById(Integer id);

    List<Driver> findAll();
    List<Driver> findAllAvailableDrivers();


    void update(Driver driver);

    void delete(Integer id);
}
