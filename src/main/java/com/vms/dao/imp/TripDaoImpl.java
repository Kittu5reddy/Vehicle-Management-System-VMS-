package com.vms.dao.imp;

import com.vms.config.HibernateUtil;
import com.vms.dao.TripDao;
import com.vms.model.Trip;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TripDaoImpl implements TripDao {

    public void save(Trip trip){

        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            Transaction tx = session.beginTransaction();

            session.persist(trip);

            tx.commit();
        }
    }

    public Trip findById(Integer id){

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(Trip.class,id);
        }
    }

    @Override
    public List<Trip> findAll() {

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            return session.createQuery(
                    """
                    SELECT t FROM Trip t
                    LEFT JOIN FETCH t.driver
                    LEFT JOIN FETCH t.vehicle
                    """,
                    Trip.class
            ).getResultList();
        }
    }

    public void update(Trip trip){

        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            Transaction tx = session.beginTransaction();

            session.merge(trip);

            tx.commit();
        }
    }

    public void delete(Integer id){

        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            Transaction tx = session.beginTransaction();

            Trip trip = session.get(Trip.class,id);

            if(trip!=null) session.remove(trip);

            tx.commit();
        }
    }
}
