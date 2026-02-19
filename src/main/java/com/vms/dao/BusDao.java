package com.vms.dao;

import com.vms.model.Bus;

public interface BusDao extends VehicleDao{

    void save(Bus bus);

    void update(Bus bus);

    void delete(Integer id);
}
