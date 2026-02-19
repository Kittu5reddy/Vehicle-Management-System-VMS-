package com.vms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "drivers")
public class Driver extends User {

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    @Override
    public String toString() {

        return super.getFullName()+" "+rating ;
    }

    @Column(nullable = false)
    private Float rating;


    @Column(nullable = false)
    private Integer experienceYears;


    @Column(nullable = false)
    private Float amountPerTrip;

    @Column(nullable = false)
    private Boolean available = true;

    @Column(nullable = false)
    private LocalDateTime licenseExpiryDate;

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }

    public Float getAmountPerTrip() {
        return amountPerTrip;
    }

    public void setAmountPerTrip(Float amountPerTrip) {
        this.amountPerTrip = amountPerTrip;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public LocalDateTime getLicenseExpiryDate() {
        return licenseExpiryDate;
    }

    public void setLicenseExpiryDate(LocalDateTime licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }
}
