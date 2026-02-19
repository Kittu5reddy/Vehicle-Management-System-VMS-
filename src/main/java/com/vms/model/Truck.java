package com.vms.model;


import com.vms.enums.TruckType;
import jakarta.persistence.*;

@Entity
@Table(name = "trucks")
public class Truck extends Vehicle {

    @Column(name = "load_capacity", nullable = false)
    private Float loadCapacity;

    @Column(name = "number_of_axles", nullable = false)
    private Integer numberOfAxles;

    @Enumerated(EnumType.STRING)
    @Column(name = "truck_type", nullable = false, length = 50)
    private TruckType truckType;


    public Truck() {}

    @Override
    public String toString() {
        return "Truck " + super.getRegistrationNumber();
    }

    public Float getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(Float loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public Integer getNumberOfAxles() {
        return numberOfAxles;
    }

    public void setNumberOfAxles(Integer numberOfAxles) {
        this.numberOfAxles = numberOfAxles;
    }

    public TruckType getTruckType() {
        return truckType;
    }

    public void setTruckType(TruckType truckType) {
        this.truckType = truckType;
    }
}
