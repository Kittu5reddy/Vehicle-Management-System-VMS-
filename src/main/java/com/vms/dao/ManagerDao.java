package com.vms.dao;

import com.vms.model.Manager;

import java.util.List;

public interface ManagerDao {

    void save(Manager manager);

    Manager findById(Integer id);

    List<Manager> findAll();

    void update(Manager manager);

    void delete(Integer id);
}
