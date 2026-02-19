package com.vms.dao;

import com.vms.model.Bus;

import java.util.List;

public interface BusDao{

    void save(Bus bus);
    List<Bus> findAll();
    void update(Bus bus);
    Bus findById(Integer id);
    void delete(Integer id);
}
