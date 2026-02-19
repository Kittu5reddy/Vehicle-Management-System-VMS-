package com.vms.service;

import com.vms.model.User;

public interface AuthService {

    User login(String userName, String password);

    void logout();
}
