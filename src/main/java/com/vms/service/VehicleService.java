package com.vms.service;

import com.vms.model.Vehicle;

import java.util.List;

public interface VehicleService {

    void saveVehicle(Vehicle vehicle);

    Vehicle getVehicleById(Integer id);

    List<Vehicle> getAllVehicles();

    void updateVehicle(Vehicle vehicle);

    void deleteVehicle(Integer id);
}
