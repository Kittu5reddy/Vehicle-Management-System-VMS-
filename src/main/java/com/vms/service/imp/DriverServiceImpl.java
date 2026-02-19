package com.vms.service.imp;

import com.vms.dao.DriverDao;
import com.vms.dao.imp.DriverDaoImpl;
import com.vms.model.Driver;
import com.vms.service.DriverService;

import java.util.List;

public class DriverServiceImpl implements DriverService {

    private DriverDao driverDao = new DriverDaoImpl();
    @Override
    public List<Driver> getAvailableDrivers() {
        return driverDao.findAllAvailableDrivers();
    }
    @Override
    public void saveDriver(Driver driver) { driverDao.save(driver); }
    @Override
    public Driver getDriverById(Integer id) { return driverDao.findById(id); }
    @Override
    public List<Driver> getAllDrivers() { return driverDao.findAll(); }
    @Override
    public void updateDriver(Driver driver) { driverDao.update(driver); }
    @Override
    public void deleteDriver(Integer id) { driverDao.delete(id); }
}
