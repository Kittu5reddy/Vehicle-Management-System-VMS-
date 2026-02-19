package com.vms.dao;

import com.vms.model.Truck;

import java.util.List;

public interface TruckDao {

    void save(Truck truck);

    Truck findById(Integer id);

    List<Truck> findAll();

    void update(Truck truck);

    void delete(Integer id);
}
