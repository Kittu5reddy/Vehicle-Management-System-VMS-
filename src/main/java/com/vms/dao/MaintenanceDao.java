package com.vms.dao;

import com.vms.model.Maintenance;

import java.util.List;

public interface MaintenanceDao {

    void save(Maintenance maintenance);

    Maintenance findById(Integer id);

    List<Maintenance> findAll();

    void update(Maintenance maintenance);

    void delete(Integer id);
}
