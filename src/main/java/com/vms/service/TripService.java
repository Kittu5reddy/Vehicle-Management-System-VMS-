package com.vms.service;

import com.vms.model.Trip;

import java.util.List;

public interface TripService {
    void saveTrip(Trip trip);
    Trip getTripById(Integer id);
    List<Trip> getAllTrips();
    void updateTrip(Trip trip);
    void deleteTrip(Integer id);
}
