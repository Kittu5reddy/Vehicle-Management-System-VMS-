package com.vms.dao;

import com.vms.model.Trip;

import java.util.List;

public interface TripDao {

    void save(Trip trip);

    Trip findById(Integer id);

    List<Trip> findAll();

    void update(Trip trip);

    void delete(Integer id);
}
