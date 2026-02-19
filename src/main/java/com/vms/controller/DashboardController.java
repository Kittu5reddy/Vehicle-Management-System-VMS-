package com.vms.controller;

import com.vms.enums.TripStatus;
import com.vms.model.Maintenance;
import com.vms.model.Trip;
import com.vms.service.DriverService;
import com.vms.service.MaintenanceService;
import com.vms.service.TripService;
import com.vms.service.VehicleService;
import com.vms.service.imp.DriverServiceImpl;
import com.vms.service.imp.MaintenanceServiceImpl;
import com.vms.service.imp.TripServiceImpl;
import com.vms.service.imp.VehicleServiceImpl;
import com.vms.ui.DashboardPanel;

import java.util.ArrayList;
import java.util.List;

public class DashboardController {

    private final DashboardPanel dashboardPanel;

    private final VehicleService vehicleService;
    private final DriverService driverService;
    private final TripService tripService;
    private final MaintenanceService maintenanceService;

    public DashboardController(DashboardPanel dashboardPanel) {

        this.dashboardPanel = dashboardPanel;

        this.vehicleService = new VehicleServiceImpl();
        this.driverService = new DriverServiceImpl();
        this.tripService = new TripServiceImpl();
        this.maintenanceService = new MaintenanceServiceImpl();
    }

    public void loadDashboardData() {

        // ================= COUNTS =================

        long vehicleCount = vehicleService.getAllVehicles().size();
        long driverCount = driverService.getAllDrivers().size();

        // Only active trips
        long activeTripCount = tripService.getAllTrips()
                .stream()
                .filter(t ->
                        t.getStatus() == TripStatus.SCHEDULED ||
                                t.getStatus() == TripStatus.IN_PROGRESS ||  t.getStatus() == TripStatus.STARTED
                )
                .count();

        // Pending maintenance
        long pendingMaintenance = maintenanceService.getAllMaintenances()
                .size(); // You can later filter by status if you add status column

        dashboardPanel.setVehicleCount(vehicleCount);
        dashboardPanel.setDriverCount(driverCount);
        dashboardPanel.setTripCount(activeTripCount);
        dashboardPanel.setMaintenanceCount(pendingMaintenance);

        // ================= RECENT TRIPS =================

        List<Trip> trips = tripService.getAllTrips();

        List<String[]> recentTripRows = new ArrayList<>();

        trips.stream()
                .sorted((a,b) ->
                        b.getStartTime().compareTo(a.getStartTime())
                )
                .limit(3)
                .forEach(t -> {

                    recentTripRows.add(new String[]{
                            t.getStartLocation() + " → " + t.getEndLocation(),
                            t.getDriver().getFullName(),
                            t.getStatus().name()
                    });
                });

        dashboardPanel.setRecentTrips(recentTripRows);

        // ================= MAINTENANCE QUEUE =================

        List<Maintenance> maintenances =
                maintenanceService.getAllMaintenances();

        List<String[]> maintenanceRows = new ArrayList<>();

        maintenances.stream()
                .sorted((a,b) ->
                        a.getServiceDate().compareTo(b.getServiceDate())
                )
                .limit(3)
                .forEach(m -> {

                    maintenanceRows.add(new String[]{
                            m.getVehicle().getRegistrationNumber(),
                            m.getServiceType(),
                            "pending"
                    });
                });

        dashboardPanel.setMaintenanceQueue(maintenanceRows);
    }
}
