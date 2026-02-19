package com.vms.service.imp;

import com.vms.dao.TruckDao;
import com.vms.dao.imp.TruckDaoImpl;
import com.vms.model.Truck;
import com.vms.service.TruckService;

import java.util.List;

public class TruckServiceImpl implements TruckService {

    private TruckDao truckDao = new TruckDaoImpl();

    public void saveTruck(Truck truck) { truckDao.save(truck); }
    public Truck getTruckById(Integer id) { return truckDao.findById(id); }
    public List<Truck> getAllTrucks() { return truckDao.findAll(); }
    public void updateTruck(Truck truck) { truckDao.update(truck); }
    public void deleteTruck(Integer id) { truckDao.delete(id); }
}
