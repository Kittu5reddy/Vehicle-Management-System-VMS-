package com.vms.dao.imp;

import com.vms.config.HibernateUtil;
import com.vms.dao.VehicleDao;
import com.vms.model.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class VehicleDaoImpl implements VehicleDao {

    @Override
    public Vehicle findById(Integer id) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.get(Vehicle.class, id);
        }
    }

    @Override
    public List<Vehicle> findAll() {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "FROM Vehicle",
                    Vehicle.class
            ).list();
        }
    }

    @Override
    public void delete(Integer id) {

        Transaction tx = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Vehicle vehicle = session.get(Vehicle.class, id);

            if (vehicle != null) {
                session.remove(vehicle);
            }

            tx.commit();

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }

            e.printStackTrace();
        }
    }
}
