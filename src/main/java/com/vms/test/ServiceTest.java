package com.vms.test;

import com.vms.enums.FuelType;
import com.vms.enums.Gender;
import com.vms.enums.VehicleStatus;
import com.vms.model.Bus;
import com.vms.model.Driver;
import com.vms.service.imp.*;

import java.time.LocalDateTime;
import java.util.List;

public class ServiceTest {

    public static void main(String[] args) {

        // Services
        BusServiceImpl busService = new BusServiceImpl();
        CarServiceImpl carService = new CarServiceImpl();
        TruckServiceImpl truckService = new TruckServiceImpl();
        DriverServiceImpl driverService = new DriverServiceImpl();
        ManagerServiceImpl managerService = new ManagerServiceImpl();
        MaintenanceServiceImpl maintenanceService = new MaintenanceServiceImpl();
        TripServiceImpl tripService = new TripServiceImpl();

        // ================= BUS =================
        Bus bus = new Bus();
        bus.setBrandName("Tata");
        bus.setModel("Service Bus");
        bus.setRegistrationNumber("TS09SERVICE1");
        bus.setFuelType(FuelType.DIESEL);
        bus.setStatus(VehicleStatus.AVAILABLE);
        bus.setPurchaseDate(LocalDateTime.now());
        bus.setSeatingCapacity(45);
        bus.setStandingCapacity(15);
        bus.setBusNumber("BUS-S1");

        busService.saveBus(bus);

        // ================= DRIVER =================
        Driver driver = new Driver();
        driver.setUserName("serviceDriver");
        driver.setFullName("Service Driver");
        driver.setPhoneNumber("6666666666");
        driver.setGender(Gender.MALE);
        driver.setPassword("pass");
        driver.setDateOfBirth(LocalDateTime.now());
        driver.setLicenseNumber("DL-SERVICE");
        driver.setRating(4.8f);
        driver.setExperienceYears(7);
        driver.setAmountPerTrip(2000f);
        driver.setAvailable(true);
        driver.setLicenseExpiryDate(LocalDateTime.now().plusYears(5));

        driverService.saveDriver(driver);

        // ================= DISPLAY =================

        System.out.println("\n===== BUS DATA =====");
        List<Bus> buses = busService.getAllBuses();
        buses.forEach(b ->
                System.out.println(b.getBusNumber() + " | " + b.getBrandName())
        );

        System.out.println("\n===== DRIVER DATA =====");
        List<Driver> drivers = driverService.getAllDrivers();
        drivers.forEach(d ->
                System.out.println(d.getFullName() + " | " + d.getLicenseNumber())
        );

        System.out.println("\n✅ SERVICE LAYER TEST SUCCESSFUL");
    }
}
