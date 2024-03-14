package com.main.laba_1.service;

import com.main.laba_1.model.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();
    Optional<User> findById(Integer id);
    User saveUser(User user);
    User updateUser(User user);
    ResponseEntity<Boolean> deleteUser(Integer id);
    ResponseEntity<User> setUserGroup(Integer userId, String groupId);
    ResponseEntity<User> userSaveGroup(Integer userId, String groupNumber);
    ResponseEntity<User> deleteUserGroup(Integer userId);
    ResponseEntity<User> deleteSavedGroup(Integer userId, String groupNumber);
}
