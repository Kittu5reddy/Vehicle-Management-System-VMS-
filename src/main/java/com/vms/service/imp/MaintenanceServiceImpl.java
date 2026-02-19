package com.vms.service.imp;

import com.vms.dao.MaintenanceDao;
import com.vms.dao.imp.MaintenanceDaoImpl;
import com.vms.model.Maintenance;
import com.vms.service.MaintenanceService;

import java.util.List;

public class MaintenanceServiceImpl implements MaintenanceService {

    private MaintenanceDao maintenanceDao = new MaintenanceDaoImpl();

    public void saveMaintenance(Maintenance maintenance) { maintenanceDao.save(maintenance); }
    public Maintenance getMaintenanceById(Integer id) { return maintenanceDao.findById(id); }
    public List<Maintenance> getAllMaintenances() { return maintenanceDao.findAll(); }
    public void updateMaintenance(Maintenance maintenance) { maintenanceDao.update(maintenance); }
    public void deleteMaintenance(Integer id) { maintenanceDao.delete(id); }
}
