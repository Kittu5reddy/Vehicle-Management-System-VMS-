package com.vms.dao;

import com.vms.model.Vehicle;

import java.util.List;

public interface VehicleDao {

    Vehicle findById(Integer id);

    List<Vehicle> findAll();

    void delete(Integer id);
}
