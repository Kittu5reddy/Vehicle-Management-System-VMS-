package com.vms.dao.imp;

import com.vms.config.HibernateUtil;
import com.vms.dao.UserDao;
import com.vms.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public User findById(Integer id) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.get(User.class, id);
        }
    }

    @Override
    public User findByUserName(String userName) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "FROM User u WHERE u.userName = :username",
                            User.class)
                    .setParameter("username", userName)
                    .uniqueResult();
        }
    }

    @Override
    public List<User> findAll() {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "FROM User",
                            User.class)
                    .list();
        }
    }

    @Override
    public void delete(Integer id) {

        Transaction tx = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            User user = session.get(User.class, id);

            if (user != null) {
                session.remove(user);
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
