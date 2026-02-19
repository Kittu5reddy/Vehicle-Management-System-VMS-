package com.vms.service.imp;

import com.vms.dao.VehicleDao;
import com.vms.dao.imp.VehicleDaoImpl;
import com.vms.model.Vehicle;
import com.vms.service.VehicleService;

import java.util.List;

public class VehicleServiceImpl implements VehicleService {

    private final VehicleDao vehicleDao;

    public VehicleServiceImpl() {
        this.vehicleDao = new VehicleDaoImpl();
    }

    @Override
    public void saveVehicle(Vehicle vehicle) {
        vehicleDao.save(vehicle);
    }

    @Override
    public Vehicle getVehicleById(Integer id) {
        return vehicleDao.findById(id);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleDao.findAll();
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        vehicleDao.update(vehicle);
    }

    @Override
    public void deleteVehicle(Integer id) {
        vehicleDao.delete(id);
    }
}
