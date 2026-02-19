package com.vms.dao.imp;

import com.vms.config.HibernateUtil;
import com.vms.dao.DriverDao;
import com.vms.model.Driver;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DriverDaoImpl implements DriverDao {

    @Override
    public void save(Driver driver) {

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            Transaction tx = session.beginTransaction();

            session.persist(driver);

            tx.commit();
        }
    }



    @Override
    public List<Driver> findAllAvailableDrivers() {

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "FROM Driver d WHERE d.available = true",
                    Driver.class
            ).list();
        }
    }


    @Override
    public Driver findById(Integer id) {

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Driver.class, id);
        }
    }

    @Override
    public List<Driver> findAll() {

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Driver", Driver.class).list();
        }
    }

    @Override
    public void update(Driver driver) {

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            Transaction tx = session.beginTransaction();

            session.merge(driver);

            tx.commit();
        }
    }

    @Override
    public void delete(Integer id) {

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            Transaction tx = session.beginTransaction();

            Driver driver = session.get(Driver.class, id);

            if(driver != null) session.remove(driver);

            tx.commit();
        }
    }
}
