package com.vms.service.imp;

import com.vms.dao.DriverDao;
import com.vms.dao.imp.DriverDaoImpl;
import com.vms.model.Driver;
import com.vms.service.DriverService;

import java.util.List;

public class DriverServiceImpl implements DriverService {

    private DriverDao driverDao = new DriverDaoImpl();

    public void saveDriver(Driver driver) { driverDao.save(driver); }
    public Driver getDriverById(Integer id) { return driverDao.findById(id); }
    public List<Driver> getAllDrivers() { return driverDao.findAll(); }
    public void updateDriver(Driver driver) { driverDao.update(driver); }
    public void deleteDriver(Integer id) { driverDao.delete(id); }
}
