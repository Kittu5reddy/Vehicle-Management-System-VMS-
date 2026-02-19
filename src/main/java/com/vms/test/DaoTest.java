package com.vms.test;

import com.vms.dao.imp.*;
import com.vms.enums.*;
import com.vms.model.*;

import java.time.LocalDateTime;
import java.util.List;

public class DaoTest {

    public static void main(String[] args) {

        BusDaoImpl busDao = new BusDaoImpl();
        CarDaoImpl carDao = new CarDaoImpl();
        TruckDaoImpl truckDao = new TruckDaoImpl();
        DriverDaoImpl driverDao = new DriverDaoImpl();
        ManagerDaoImpl managerDao = new ManagerDaoImpl();
        MaintenanceDaoImpl maintenanceDao = new MaintenanceDaoImpl();
        TripDaoImpl tripDao = new TripDaoImpl();

        // ================= BUS =================
        Bus bus = new Bus();
        bus.setBrandName("Tata");
        bus.setModel("City Bus");
        bus.setRegistrationNumber("TS09AA1001");
        bus.setFuelType(FuelType.DIESEL);
        bus.setStatus(VehicleStatus.AVAILABLE);
        bus.setPurchaseDate(LocalDateTime.now());
        bus.setSeatingCapacity(50);
        bus.setStandingCapacity(20);
        bus.setBusNumber("BUS-1001");

        busDao.save(bus);

        // ================= CAR =================
        Car car = new Car();
        car.setBrandName("Hyundai");
        car.setModel("i20");
        car.setRegistrationNumber("TS09AA2001");
        car.setFuelType(FuelType.PETROL);
        car.setStatus(VehicleStatus.AVAILABLE);
        car.setPurchaseDate(LocalDateTime.now());
        car.setNumberOfDoors(4);
        car.setHasAC(true);

        carDao.save(car);

        // ================= TRUCK =================
        Truck truck = new Truck();
        truck.setBrandName("Ashok Leyland");
        truck.setModel("Heavy");
        truck.setRegistrationNumber("TS09AA3001");
        truck.setFuelType(FuelType.DIESEL);
        truck.setStatus(VehicleStatus.AVAILABLE);
        truck.setPurchaseDate(LocalDateTime.now());
        truck.setLoadCapacity(2000f);
        truck.setNumberOfAxles(6);
        truck.setTruckType(TruckType.HEAVY_DUTY);

        truckDao.save(truck);

        // ================= DRIVER =================
        Driver driver = new Driver();
        driver.setUserName("driver1");
        driver.setFullName("Ravi Kumar");
        driver.setPhoneNumber("9999999999");
        driver.setGender(Gender.MALE);
        driver.setPassword("pass");
        driver.setDateOfBirth(LocalDateTime.now());

        driver.setLicenseNumber("DL12345678");
        driver.setRating(4.5f);
        driver.setExperienceYears(5);
        driver.setAmountPerTrip(1500f);
        driver.setAvailable(true);
        driver.setLicenseExpiryDate(LocalDateTime.now().plusYears(2));

        driverDao.save(driver);

        // ================= MANAGER =================
        Manager manager = new Manager();
        manager.setUserName("manager1");
        manager.setFullName("Kiran Kumar");
        manager.setPhoneNumber("8888888888");
        manager.setGender(Gender.MALE);
        manager.setPassword("pass");
        manager.setDateOfBirth(LocalDateTime.now());
        manager.setDepartment("Operations");
        manager.setSalary(50000f);
        manager.setExperienceYears(8);
        manager.setJoiningDate(LocalDateTime.now());

        managerDao.save(manager);

        // ================= MAINTENANCE =================
        Maintenance maintenance = new Maintenance();
        maintenance.setVehicle(bus);
        maintenance.setServiceType("Oil Change");
        maintenance.setServiceDate(LocalDateTime.now());
        maintenance.setCost(2500.0);
        maintenance.setServiceCenter("Hyderabad Service Center");

        maintenanceDao.save(maintenance);

        // ================= TRIP =================
        Trip trip = new Trip();
        trip.setVehicle(bus);
        trip.setDriver(driver);
        trip.setStartLocation("Hyderabad");
        trip.setEndLocation("Warangal");
        trip.setStartTime(LocalDateTime.now());
        trip.setStatus(TripStatus.SCHEDULED);
        trip.setDistance(150f);

        tripDao.save(trip);

        System.out.println("\n===== DISPLAY DATA =====");

        // DISPLAY BUS
        List<Bus> buses = busDao.findAll();
        buses.forEach(b ->
                System.out.println("Bus: " + b.getBusNumber() + " | " + b.getBrandName())
        );

        // DISPLAY CAR
        List<Car> cars = carDao.findAll();
        cars.forEach(c ->
                System.out.println("Car: " + c.getModel() + " | " + c.getRegistrationNumber())
        );

        // DISPLAY TRUCK
        List<Truck> trucks = truckDao.findAll();
        trucks.forEach(t ->
                System.out.println("Truck: " + t.getTruckType() + " | Axles: " + t.getNumberOfAxles())
        );

        // DISPLAY DRIVER
        List<Driver> drivers = driverDao.findAll();
        drivers.forEach(d ->
                System.out.println("Driver: " + d.getFullName() + " | License: " + d.getLicenseNumber())
        );

        // DISPLAY MANAGER
        List<Manager> managers = managerDao.findAll();
        managers.forEach(m ->
                System.out.println("Manager: " + m.getFullName() + " | Dept: " + m.getDepartment())
        );

        // DISPLAY MAINTENANCE
        List<Maintenance> maintenances = maintenanceDao.findAll();
        maintenances.forEach(m ->
                System.out.println("Maintenance: " + m.getServiceType())
        );

        // DISPLAY TRIP
        List<Trip> trips = tripDao.findAll();
        trips.forEach(t ->
                System.out.println("Trip: " + t.getStartLocation() + " -> " + t.getEndLocation())
        );

        System.out.println("\n✅ ALL DAO TEST DATA INSERTED & DISPLAYED SUCCESSFULLY");
    }
}
