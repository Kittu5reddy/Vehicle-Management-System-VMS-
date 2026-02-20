package com.vms.controller;

import com.vms.enums.Gender;
import com.vms.enums.Role;
import com.vms.model.Driver;
import com.vms.security.PasswordUtil;
import com.vms.service.DriverService;
import com.vms.service.imp.DriverServiceImpl;
import com.vms.ui.DriversPanel;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class DriverController {

    private final DriversPanel view;
    private final DriverService driverService;

    private final DashboardController dashboardController;

    public DriverController(DriversPanel view,
                            DashboardController dashboardController) {
        this.view = view;
        this.driverService = new DriverServiceImpl();
        this.dashboardController = dashboardController;
    }
    // ==================================
    // LOAD DRIVERS
    // ==================================

    public void loadDrivers() {

        List<Driver> drivers = driverService.getAllDrivers();

        view.clearTable();

        for (Driver d : drivers) {

            view.addDriverRow(new Object[]{
                    d.getId(),
                    d.getFullName(),
                    d.getLicenseNumber(),
                    d.getLicenseExpiryDate(),
                    d.getAvailable() ? "Available" : "Assigned"
            });
        }
    }

    // ==================================
    // CREATE DRIVER
    // ==================================

    public void createDriver(
            String fullName,
            String userName,
            String password,
            String licenseNumber,
            Date expiryDate,
            Gender gender,
            String phoneNumber,
            String address,
            String email,
            Date dob,
            int experienceYears,
            float rating,
            float amountPerTrip,
            String status
    ){
        try {

            Driver driver = new Driver();

            driver.setUserName(userName);
            driver.setFullName(fullName);
            driver.setPhoneNumber(phoneNumber);
            driver.setEmail(email);
            driver.setPassword(PasswordUtil.hashPassword(password));
            driver.setGender(gender);
            driver.setRole(Role.DRIVER);

            driver.setDateOfBirth(convertDateToLocalDateTime(dob));
            driver.setAddress(address);

            driver.setLicenseNumber(licenseNumber);
            driver.setLicenseExpiryDate(convertDateToLocalDateTime(expiryDate));
            driver.setExperienceYears(experienceYears);
            driver.setRating(rating);
            driver.setAmountPerTrip(amountPerTrip);
            driver.setAvailable(status.equalsIgnoreCase("Available"));

            driverService.saveDriver(driver);

            loadDrivers();
            dashboardController.loadDashboardData();
        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    null,
                    "Create Failed:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // ==================================
    // UPDATE DRIVER
    // ==================================

    public void updateDriver(
            Integer id,
            String fullName,
            String userName,
            String password,
            String licenseNumber,
            Date expiryDate,
            Gender gender,
            String phone,
            String address,
            String email,
            Date dob,
            int experienceYears,
            float rating,
            float amountPerTrip,
            String status
    ){
        try{

            Driver driver = driverService.getDriverById(id);

            if(driver == null){
                JOptionPane.showMessageDialog(null,"Driver not found!","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }

            driver.setFullName(fullName);
            driver.setUserName(userName);

            if(password != null && !password.isBlank()){
                driver.setPassword(PasswordUtil.hashPassword(password));
            }

            driver.setPhoneNumber(phone);
            driver.setAddress(address);
            driver.setEmail(email);
            driver.setGender(gender);
            driver.setDateOfBirth(convertDateToLocalDateTime(dob));
            driver.setRole(Role.DRIVER);

            driver.setLicenseNumber(licenseNumber);
            driver.setLicenseExpiryDate(convertDateToLocalDateTime(expiryDate));
            driver.setExperienceYears(experienceYears);
            driver.setRating(rating);
            driver.setAmountPerTrip(amountPerTrip);
            driver.setAvailable(status.equalsIgnoreCase("Available"));

            driverService.updateDriver(driver);

            loadDrivers();
            AbstractButton addDriverButton;
            dashboardController.loadDashboardData();

        }
        catch(Exception ex){

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    null,
                    "Update Failed:\n"+ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // ==================================
    // DELETE DRIVER
    // ==================================

    public void deleteDriver(Integer id){
        driverService.deleteDriver(id);
        loadDrivers();
        loadDrivers();
        dashboardController.loadDashboardData();
    }

    public Driver getDriverById(Integer id){
        return driverService.getDriverById(id);
    }

    // ==================================
    // HELPER METHODS
    // ==================================

    private LocalDateTime convertDateToLocalDateTime(Date date){

        if(date == null) return null;

        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    private LocalDateTime parseStringToLocalDateTime(String date){

        if(date == null || date.isBlank()) return null;

        return LocalDateTime.parse(date);
    }
}
