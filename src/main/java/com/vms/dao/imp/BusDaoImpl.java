package com.vms.dao.imp;

import com.vms.dao.BusDao;
import com.vms.model.Bus;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static com.vms.config.HibernateUtil.getSessionFactory;

public class BusDaoImpl implements BusDao {

    @Override
    public void save(Bus bus) {

        Transaction tx = null;

        try (Session session = getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            session.persist(bus);

            tx.commit();

        } catch (Exception e) {

            if (tx != null) tx.rollback();

            e.printStackTrace();
        }
    }

    @Override
    public List<Bus> findAll() {

        try (Session session = getSessionFactory().openSession()) {

            return session.createQuery("FROM Bus", Bus.class)
                    .getResultList();
        }
    }

    @Override
    public Bus findById(Integer id) {

        try (Session session = getSessionFactory().openSession()) {

            return session.get(Bus.class, id);
        }
    }

    @Override
    public void update(Bus bus) {

        Transaction tx = null;

        try (Session session = getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            session.merge(bus);

            tx.commit();

        } catch (Exception e) {

            if (tx != null) tx.rollback();

            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {

        Transaction tx = null;

        try (Session session = getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Bus bus = session.get(Bus.class, id);

            if (bus != null) {
                session.remove(bus);
            }

            tx.commit();

        } catch (Exception e) {

            if (tx != null) tx.rollback();

            e.printStackTrace();
        }
    }
}
