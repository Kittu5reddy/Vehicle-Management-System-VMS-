package com.vms.config;

import com.vms.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Vehicle.class)
                .addAnnotatedClass(Car.class)
                .addAnnotatedClass(Bus.class)
                .addAnnotatedClass(Truck.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Driver.class)
                .addAnnotatedClass(Manager.class)
                .addAnnotatedClass(Maintenance.class)
                .addAnnotatedClass(Trip.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
