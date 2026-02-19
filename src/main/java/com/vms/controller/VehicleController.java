package com.vms.controller;

import com.vms.enums.FuelType;
import com.vms.enums.VehicleStatus;
import com.vms.model.Bus;
import com.vms.model.Car;
import com.vms.model.Truck;
import com.vms.model.Vehicle;
import com.vms.service.VehicleService;
import com.vms.service.imp.VehicleServiceImpl;
import com.vms.ui.VehiclesPanel;

import java.time.LocalDateTime;
import java.util.List;

public class VehicleController {

    private final VehicleService vehicleService;
    private final VehiclesPanel view;

    public VehicleController(VehiclesPanel view) {
        this.view = view;
        this.vehicleService = new VehicleServiceImpl();
    }

    // =============================
    // LOAD DATA INTO TABLE
    // =============================
    public void loadVehicles() {

        List<Vehicle> vehicles = vehicleService.getAllVehicles();

        view.clearTable();

        for (Vehicle v : vehicles) {

            view.addVehicleRow(new Object[]{
                    v.getId(),
                    v.getRegistrationNumber(),
                    v.getBrandName(),
                    v.getModel(),
                    v.getFuelType().name(),
                    v.getStatus().name(),
                    v.getPurchaseDate()
            });
        }
    }
    public void updateVehicle(
            Integer id,
            String number,
            String brand,
            String model,
            String fuel,
            String status,
            String purchaseDate
    ){

        Vehicle v = vehicleService.getVehicleById(id);

        v.setRegistrationNumber(number);
        v.setBrandName(brand);
        v.setModel(model);
        v.setFuelType(FuelType.valueOf(fuel));
        v.setStatus(VehicleStatus.valueOf(status));
        v.setPurchaseDate(LocalDateTime.parse(purchaseDate));

        vehicleService.updateVehicle(v);

        loadVehicles();
    }

    // =============================
    // CREATE VEHICLE
    // =============================
    public void createVehicle(
            String number,
            String brand,
            String model,
            String fuel,
            String status,
            String purchaseDate
    ) {

        Vehicle vehicle = new Vehicle();

        vehicle.setRegistrationNumber(number);
        vehicle.setBrandName(brand);
        vehicle.setModel(model);
        vehicle.setFuelType(FuelType.valueOf(fuel));
        vehicle.setStatus(VehicleStatus.valueOf(status));
        vehicle.setPurchaseDate(LocalDateTime.parse(purchaseDate));

        vehicleService.saveVehicle(vehicle);

        loadVehicles(); // refresh table
    }

    // =============================
    // DELETE VEHICLE
    // =============================
    public void deleteVehicle(Integer id) {

        vehicleService.deleteVehicle(id);
        loadVehicles();
    }

    // =============================
    // UPDATE VEHICLE
    // =============================
    public void updateVehicle(Vehicle vehicle) {

        vehicleService.updateVehicle(vehicle);
        loadVehicles();
    }
    public Vehicle getVehicleById(Integer id){
        return vehicleService.getVehicleById(id);
    }
    public void createVehicleWithType(
            String type,
            String number,
            String brand,
            String model,
            String fuel,
            String status,
            String purchase,
            String doors,
            String boot,
            String load,
            String axle,
            String seating,
            String standing
    ){
        Vehicle v;

        if(type.equals("CAR")){
            Car car = new Car();
            car.setNumberOfDoors(Integer.parseInt(doors));
            car.setBootSpace((float) Double.parseDouble(boot));
            v = car;

        } else if(type.equals("TRUCK")){
            Truck truck = new Truck();
            truck.setLoadCapacity((float) Double.parseDouble(load));
            truck.setNumberOfAxles(Integer.parseInt(axle));
            v = truck;

        } else {
            Bus bus = new Bus();
            bus.setSeatingCapacity(Integer.parseInt(seating));
            bus.setStandingCapacity(Integer.parseInt(standing));
            v = bus;
        }

        v.setRegistrationNumber(number);
        v.setBrandName(brand);
        v.setModel(model);
        v.setFuelType(FuelType.valueOf(fuel));
        v.setStatus(VehicleStatus.valueOf(status));
        v.setPurchaseDate(LocalDateTime.parse(purchase));

        vehicleService.saveVehicle(v);

        loadVehicles();
    }
    public void updateVehicleWithType(
            Integer id,
            String type,
            String number,
            String brand,
            String model,
            String fuel,
            String status,
            String purchase,
            String doors,
            String boot,
            String load,
            String axle,
            String seating,
            String standing
    ){

        Vehicle v = vehicleService.getVehicleById(id);

        if(v == null){
            throw new RuntimeException("Vehicle not found");
        }

        // Common fields
        v.setRegistrationNumber(number);
        v.setBrandName(brand);
        v.setModel(model);
        v.setFuelType(FuelType.valueOf(fuel));
        v.setStatus(VehicleStatus.valueOf(status));
        v.setPurchaseDate(LocalDateTime.parse(purchase));

        // ⭐ SAFE subtype update
        if(v instanceof Car car){

            if(!doors.isBlank())
                car.setNumberOfDoors(Integer.parseInt(doors));

            if(!boot.isBlank())
                car.setBootSpace((float) Double.parseDouble(boot));

        }
        else if(v instanceof Truck truck){

            if(!load.isBlank())
                truck.setLoadCapacity((float) Double.parseDouble(load));

            if(!axle.isBlank())
                truck.setNumberOfAxles(Integer.parseInt(axle));
        }
        else if(v instanceof Bus bus){

            if(!seating.isBlank())
                bus.setSeatingCapacity(Integer.parseInt(seating));

            if(!standing.isBlank())
                bus.setStandingCapacity(Integer.parseInt(standing));
        }

        vehicleService.updateVehicle(v);

        loadVehicles();
    }

}
