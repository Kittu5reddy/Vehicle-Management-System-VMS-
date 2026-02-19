package com.vms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class Car extends Vehicle {

    private Integer numberOfDoors;
    private Float bootSpace;
    private Boolean hasAC;

    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public Float getBootSpace() {
        return bootSpace;
    }

    public void setBootSpace(Float bootSpace) {
        this.bootSpace = bootSpace;
    }

    public Boolean getHasAC() {
        return hasAC;
    }

    public void setHasAC(Boolean hasAC) {
        this.hasAC = hasAC;
    }
}
