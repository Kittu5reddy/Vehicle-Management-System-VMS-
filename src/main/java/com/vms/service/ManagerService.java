package com.vms.service;

import com.vms.model.Manager;

import java.util.List;

public interface ManagerService {
    void saveManager(Manager manager);
    Manager getManagerById(Integer id);
    List<Manager> getAllManagers();
    void updateManager(Manager manager);
    void deleteManager(Integer id);
}
