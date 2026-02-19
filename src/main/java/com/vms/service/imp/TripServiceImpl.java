package com.vms.service.imp;

import com.vms.dao.TripDao;
import com.vms.dao.imp.TripDaoImpl;
import com.vms.model.Trip;
import com.vms.service.TripService;

import java.util.List;

public class TripServiceImpl implements TripService {

    private TripDao tripDao = new TripDaoImpl();

    public void saveTrip(Trip trip) { tripDao.save(trip); }
    public Trip getTripById(Integer id) { return tripDao.findById(id); }
    public List<Trip> getAllTrips() { return tripDao.findAll(); }
    public void updateTrip(Trip trip) { tripDao.update(trip); }
    public void deleteTrip(Integer id) { tripDao.delete(id); }
}
