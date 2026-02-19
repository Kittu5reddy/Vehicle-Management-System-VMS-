package com.vms.service;

import com.vms.model.Truck;

import java.util.List;

public interface TruckService {
    void saveTruck(Truck truck);
    Truck getTruckById(Integer id);
    List<Truck> getAllTrucks();
    void updateTruck(Truck truck);
    void deleteTruck(Integer id);
}
