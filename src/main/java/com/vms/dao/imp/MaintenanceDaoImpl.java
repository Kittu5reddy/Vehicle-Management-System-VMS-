package com.vms.dao.imp;

import com.vms.config.HibernateUtil;
import com.vms.dao.MaintenanceDao;
import com.vms.model.Maintenance;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MaintenanceDaoImpl implements MaintenanceDao {

    public void save(Maintenance maintenance){

        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            Transaction tx = session.beginTransaction();

            session.persist(maintenance);

            tx.commit();
        }
    }

    public Maintenance findById(Integer id){

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(Maintenance.class,id);
        }
    }

    public List<Maintenance> findAll(){

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Maintenance",Maintenance.class).list();
        }
    }

    public void update(Maintenance maintenance){

        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            Transaction tx = session.beginTransaction();

            session.merge(maintenance);

            tx.commit();
        }
    }

    public void delete(Integer id){

        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            Transaction tx = session.beginTransaction();

            Maintenance maintenance = session.get(Maintenance.class,id);

            if(maintenance!=null) session.remove(maintenance);

            tx.commit();
        }
    }
}
