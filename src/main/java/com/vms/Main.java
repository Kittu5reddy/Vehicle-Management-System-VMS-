package com.vms;


import com.vms.dao.ManagerDao;
import com.vms.dao.imp.ManagerDaoImpl;
import com.vms.enums.Gender;
import com.vms.enums.Role;
import com.vms.model.Manager;
import com.vms.security.PasswordUtil;
import com.vms.ui.MainFrame;

import javax.swing.*;
import java.time.LocalDateTime;

public class Main {
    public static void createDefaultManager(){

        Manager manager = new Manager();
        ManagerDao managerDao=new ManagerDaoImpl();
        // ===== USER FIELDS =====
        manager.setUserName("kaushikpalvai");
        manager.setPassword(PasswordUtil.hashPassword("Password@123"));

        manager.setRole(Role.MANAGER);   // IMPORTANT
        manager.setFullName("Kaushik Palvai");

        manager.setPhoneNumber("897831286"); // unique required
        manager.setEmail("kaushikpalvai@gmail.com");

        manager.setGender(Gender.MALE);

        manager.setDateOfBirth(LocalDateTime.of(
                2000,1,1,0,0
        ));

        manager.setAddress("Hyderabad");

        // ===== MANAGER FIELDS =====
        manager.setSalary(50000f);
        manager.setExperienceYears(5);
        manager.setDepartment("Operations");

        manager.setJoiningDate(LocalDateTime.now());

        // ===== SAVE =====
        managerDao.save(manager);
        System.out.println("created");
    }

    public static void main(String[] args) {
//        createDefaultManager();
        System.out.println(PasswordUtil.hashPassword("Password@123"));
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
    }
