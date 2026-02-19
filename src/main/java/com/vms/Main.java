package com.vms;

import com.vms.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            System.out.println("✅ Hibernate connected successfully!");

            transaction.commit();

        } catch (Exception e) {
            System.out.println("❌ Connection failed");
            e.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }
}