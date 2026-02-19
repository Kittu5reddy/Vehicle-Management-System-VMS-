package com.vms.model;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import com.vms.enums.TruckType;

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
