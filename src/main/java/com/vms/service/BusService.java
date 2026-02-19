package com.vms.service;

import com.vms.model.Bus;

import java.util.List;

public interface BusService {

    void saveBus(Bus bus);

    Bus getBusById(Integer id);

    List<Bus> getAllBuses();

    void updateBus(Bus bus);

    void deleteBus(Integer id);
}
