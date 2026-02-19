package com.vms.dao;

import com.vms.model.User;

import java.util.List;

public interface UserDao {

    User findById(Integer id);

    List<User> findAll();

    void delete(Integer id);
}
