package com.vms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "buses")
public class Bus extends Vehicle {

    private Integer seatingCapacity;
    private Integer standingCapacity;

    @Column(unique = true)
    private String busNumber;
    public String toString() {
        return "Bus " + super.getRegistrationNumber();
    }
    public Integer getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(Integer seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public Integer getStandingCapacity() {
        return standingCapacity;
    }

    public void setStandingCapacity(Integer standingCapacity) {
        this.standingCapacity = standingCapacity;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }
}
