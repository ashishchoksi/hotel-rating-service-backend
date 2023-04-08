package com.example.user.service.services;

import com.example.user.service.entities.User;

import java.util.List;

public interface UserService {

    // save user
    User save(User user);

    // get ALl users
    List<User> getAllUsers();

    // get single user
    User getUser(String userId);

}
