package com.vms.dao.imp;

import com.vms.config.HibernateUtil;
import com.vms.dao.ManagerDao;
import com.vms.model.Manager;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class ManagerDaoImpl implements ManagerDao {

    public void save(Manager manager){

        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            Transaction tx = session.beginTransaction();

            session.persist(manager);

            tx.commit();
        }
    }

    public Manager findById(Integer id){

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(Manager.class,id);
        }
    }

    public List<Manager> findAll(){

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Manager",Manager.class).list();
        }
    }

    public void update(Manager manager){

        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            Transaction tx = session.beginTransaction();

            session.merge(manager);

            tx.commit();
        }
    }

    public void delete(Integer id){

        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            Transaction tx = session.beginTransaction();

            Manager manager = session.get(Manager.class,id);

            if(manager!=null) session.remove(manager);

            tx.commit();
        }
    }
}
