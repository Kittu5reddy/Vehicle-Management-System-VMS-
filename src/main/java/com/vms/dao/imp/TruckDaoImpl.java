package com.vms.dao.imp;
public class TruckDaoImpl implements TruckDao {

    public void save(Truck truck){
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            Transaction tx=session.beginTransaction();
            session.persist(truck);
            tx.commit();
        }
    }

    public Truck findById(Integer id){
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            return session.get(Truck.class,id);
        }
    }

    public List<Truck> findAll(){
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Truck",Truck.class).list();
        }
    }

    public void update(Truck truck){
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            Transaction tx=session.beginTransaction();
            session.merge(truck);
            tx.commit();
        }
    }

    public void delete(Integer id){
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            Transaction tx=session.beginTransaction();
            Truck truck=session.get(Truck.class,id);
            if(truck!=null) session.remove(truck);
            tx.commit();
        }
    }
}
