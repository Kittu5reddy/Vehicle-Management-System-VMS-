package com.vms.dao.imp;

public class BusDaoImpl implements BusDao {

    public void save(Bus bus){
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            Transaction tx=session.beginTransaction();
            session.persist(bus);
            tx.commit();
        }
    }

    public Bus findById(Integer id){
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            return session.get(Bus.class,id);
        }
    }

    public List<Bus> findAll(){
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Bus",Bus.class).list();
        }
    }

    public void update(Bus bus){
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            Transaction tx=session.beginTransaction();
            session.merge(bus);
            tx.commit();
        }
    }

    public void delete(Integer id){
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            Transaction tx=session.beginTransaction();
            Bus bus=session.get(Bus.class,id);
            if(bus!=null) session.remove(bus);
            tx.commit();
        }
    }
}
