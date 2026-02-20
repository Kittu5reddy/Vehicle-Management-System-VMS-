package com.vms.controller;

import com.vms.enums.TripStatus;
import com.vms.model.Driver;
import com.vms.model.Trip;
import com.vms.model.Vehicle;
import com.vms.service.DriverService;
import com.vms.service.TripService;
import com.vms.service.VehicleService;
import com.vms.service.imp.DriverServiceImpl;
import com.vms.service.imp.TripServiceImpl;
import com.vms.service.imp.VehicleServiceImpl;
import com.vms.ui.TripsPanel;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class TripController {

    private final TripsPanel view;
    private final TripService tripService;
    private final DriverService driverService;
    private final VehicleService vehicleService;
    private final DashboardController dashboardController;

    public TripController(TripsPanel view,DashboardController dashboardController) {
        this.dashboardController=dashboardController;
        this.view = view;
        this.tripService = new TripServiceImpl();
        this.driverService = new DriverServiceImpl();
        this.vehicleService = new VehicleServiceImpl();
    }

    // ======================================
    // LOAD TRIPS INTO TABLE
    // ======================================

    public void loadTrips() {

        List<Trip> trips = tripService.getAllTrips();

        view.clearTable();

        for (Trip t : trips) {

            view.addTripRow(new Object[]{
                    t.getId(),
                    t.getStartLocation(),
                    t.getEndLocation(),
                    t.getDriver() != null ? t.getDriver().getFullName() : "-",
                    t.getVehicle() != null ? t.getVehicle().getRegistrationNumber() : "-",
                    t.getStatus()
            });
        }
    }

    // ======================================
    // CREATE OR UPDATE TRIP
    // ======================================

    public void createOrUpdateTrip(
            Integer id,
            String startLocation,
            String endLocation,
            Driver driver,
            Vehicle vehicle,
            Date startTime,
            Date endTime,
            Float distance,
            TripStatus status
    ) {

        try {

            Trip trip;

            // 🔁 UPDATE MODE
            if (id != null) {

                trip = tripService.getTripById(id);

                if (trip == null) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Trip not found!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

            } else {
                // ➕ CREATE MODE
                trip = new Trip();
            }

            // ===== BASIC FIELDS =====

            trip.setStartLocation(startLocation);
            trip.setEndLocation(endLocation);
            trip.setDriver(driver);
            trip.setVehicle(vehicle);
            trip.setStartTime(convertToLocalDateTime(startTime));
            trip.setEndTime(convertToLocalDateTime(endTime));
            trip.setDistance(distance);
            trip.setStatus(status);

            if (id == null) {
                trip.setCreatedAt(LocalDateTime.now());
                tripService.saveTrip(trip);
            } else {
                trip.setUpdatedAt(LocalDateTime.now());
                tripService.updateTrip(trip);
            }

            loadTrips();
            dashboardController.loadDashboardData();
        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    null,
                    "Operation Failed:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ======================================
    // DELETE TRIP
    // ======================================

    public void deleteTrip(Integer id) {

        tripService.deleteTrip(id);
        loadTrips();
        dashboardController.loadDashboardData();
    }

    // ======================================
    // GET SINGLE TRIP
    // ======================================

    public Trip getTripById(Integer id) {
        return tripService.getTripById(id);
    }

    // ======================================
    // DROPDOWN DATA
    // ======================================

    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    // ======================================
    // DATE CONVERSION
    // ======================================

    private LocalDateTime convertToLocalDateTime(Date date) {

        if (date == null) return null;

        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
