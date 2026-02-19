package com.vms.service.imp;

import com.vms.dao.UserDao;
import com.vms.dao.imp.UserDaoImpl;
import com.vms.model.User;
import com.vms.security.PasswordUtil;
import com.vms.service.AuthService;
import com.vms.session.AppSession;

public class AuthServiceImpl implements AuthService {

    private UserDao userDao;

    // Constructor injection (cleaner)
    public AuthServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public User login(String userName, String password) {

        if(userName == null || userName.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if(password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        User user = userDao.findByUserName(userName);

        if(user == null) {
            throw new RuntimeException("Invalid username or password");
        }

        if(!PasswordUtil.checkPassword(password, user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // Save session
        AppSession.getInstance().setUser(user);

        return user;
    }



    @Override
    public void logout() {

        // Clear Swing session
        AppSession.getInstance().clear();

        System.out.println("User logged out successfully");
    }
}
