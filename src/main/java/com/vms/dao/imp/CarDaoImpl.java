package com.vms.dao.imp;

public class CarDaoImpl implements CarDao {

    public void save(Car car){
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            Transaction tx=session.beginTransaction();
            session.persist(car);
            tx.commit();
        }
    }

    public Car findById(Integer id){
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            return session.get(Car.class,id);
        }
    }

    public List<Car> findAll(){
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Car",Car.class).list();
        }
    }

    public void update(Car car){
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            Transaction tx=session.beginTransaction();
            session.merge(car);
            tx.commit();
        }
    }

    public void delete(Integer id){
        try(Session session=HibernateUtil.getSessionFactory().openSession()){
            Transaction tx=session.beginTransaction();
            Car car=session.get(Car.class,id);
            if(car!=null) session.remove(car);
            tx.commit();
        }
    }
}
