package com.vms.service;

import com.vms.model.Maintenance;

import java.util.List;

public interface MaintenanceService {
    void saveMaintenance(Maintenance maintenance);
    Maintenance getMaintenanceById(Integer id);
    List<Maintenance> getAllMaintenances();
    void updateMaintenance(Maintenance maintenance);
    void deleteMaintenance(Integer id);
}
