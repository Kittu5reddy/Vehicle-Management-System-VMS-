package com.vms.dao;

import com.vms.model.User;

import java.util.List;

public interface UserDao {

    User findById(Integer id);
    User findByUserName(String userName);
    List<User> findAll();

    void delete(Integer id);
}



