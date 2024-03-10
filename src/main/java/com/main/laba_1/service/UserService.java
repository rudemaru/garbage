package com.main.laba_1.service;

import com.main.laba_1.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();
    Optional<User> findById(Integer id);
    User saveUser(User user);
    User updateUser(User user);
    void deleteUser(Integer id);
}
