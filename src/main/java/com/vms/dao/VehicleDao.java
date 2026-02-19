package com.vms.dao;

import com.vms.model.Vehicle;

import java.util.List;

public interface VehicleDao {

    Vehicle findById(Integer id);
    void save(Vehicle vehicle);

    List<Vehicle> findAll();
    void update(Vehicle vehicle);

    void delete(Integer id);
}
