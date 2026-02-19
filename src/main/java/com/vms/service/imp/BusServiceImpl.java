package com.vms.service.imp;

import com.vms.dao.BusDao;
import com.vms.dao.imp.BusDaoImpl;
import com.vms.model.Bus;
import com.vms.service.BusService;

import java.util.List;

public class BusServiceImpl implements BusService {

    private BusDao busDao = new BusDaoImpl();

    @Override
    public void saveBus(Bus bus) {
        busDao.save(bus);
    }

    @Override
    public Bus getBusById(Integer id) {
        return busDao.findById(id);
    }

    @Override
    public List<Bus> getAllBuses() {
        return busDao.findAll();
    }

    @Override
    public void updateBus(Bus bus) {
        busDao.update(bus);
    }

    @Override
    public void deleteBus(Integer id) {
        busDao.delete(id);
    }
}
