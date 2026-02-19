package com.vms.controller;

import com.vms.model.Maintenance;
import com.vms.model.Vehicle;
import com.vms.service.MaintenanceService;
import com.vms.service.VehicleService;
import com.vms.service.imp.MaintenanceServiceImpl;
import com.vms.service.imp.VehicleServiceImpl;
import com.vms.ui.MaintenancePanel;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class MaintenanceController {

    private MaintenancePanel view;

    private MaintenanceService maintenanceService;
    private VehicleService vehicleService;

    public MaintenanceController(MaintenancePanel view){

        this.view = view;

        maintenanceService = new MaintenanceServiceImpl();
        vehicleService = new VehicleServiceImpl();
    }

    // ================= LOAD =================

    public void loadMaintenances(){

        List<Maintenance> list = maintenanceService.getAllMaintenances();

        view.clearTable();

        for(Maintenance m : list){

            view.addRow(new Object[]{

                    m.getId(),
                    m.getVehicle().getRegistrationNumber(),
                    m.getServiceType(),
                    m.getDescription(),
                    m.getServiceDate(),
                    m.getCost(),
                    m.getServiceCenter()
            });
        }
    }

    // ================= CREATE =================

    public void createMaintenance(
            Vehicle vehicle,
            String serviceType,
            String description,
            Date serviceDate,
            Double cost,
            String serviceCenter
    ){

        Maintenance m = new Maintenance();

        m.setVehicle(vehicle);
        m.setServiceType(serviceType);
        m.setDescription(description);
        m.setServiceDate(convertDate(serviceDate));
        m.setCost(cost);
        m.setServiceCenter(serviceCenter);

        maintenanceService.saveMaintenance(m);

        loadMaintenances();
    }

    // ================= UPDATE =================

    public void updateMaintenance(

            Integer id,
            Vehicle vehicle,
            String serviceType,
            String description,
            Date serviceDate,
            Double cost,
            String serviceCenter
    ){

        Maintenance m = maintenanceService.getMaintenanceById(id);

        if(m == null){

            JOptionPane.showMessageDialog(null,"Maintenance not found");
            return;
        }

        m.setVehicle(vehicle);
        m.setServiceType(serviceType);
        m.setDescription(description);
        m.setServiceDate(convertDate(serviceDate));
        m.setCost(cost);
        m.setServiceCenter(serviceCenter);

        maintenanceService.updateMaintenance(m);

        loadMaintenances();
    }

    // ================= GET VEHICLES =================

    public List<Vehicle> getAllVehicles(){

        return vehicleService.getAllVehicles();
    }

    // ================= GET BY ID =================

    public Maintenance getById(Integer id){

        return maintenanceService.getMaintenanceById(id);
    }

    // ================= HELPER =================

    private LocalDateTime convertDate(Date date){

        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
