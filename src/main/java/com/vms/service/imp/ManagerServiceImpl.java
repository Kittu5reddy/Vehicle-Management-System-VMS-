package com.vms.service.imp;

import com.vms.dao.ManagerDao;
import com.vms.dao.imp.ManagerDaoImpl;
import com.vms.model.Manager;
import com.vms.service.ManagerService;

import java.util.List;

public class ManagerServiceImpl implements ManagerService {

    private ManagerDao managerDao = new ManagerDaoImpl();

    public void saveManager(Manager manager) { managerDao.save(manager); }
    public Manager getManagerById(Integer id) { return managerDao.findById(id); }
    public List<Manager> getAllManagers() { return managerDao.findAll(); }
    public void updateManager(Manager manager) { managerDao.update(manager); }
    public void deleteManager(Integer id) { managerDao.delete(id); }
}
