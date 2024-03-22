package com.main.laba_1.service;

import com.main.laba_1.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findById(Integer id);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(Integer id);
    void setUserGroup(Integer userId, String groupId);
    void userSaveGroup(Integer userId, String groupNumber);
    void deleteUserGroup(Integer userId);
    void deleteSavedGroup(Integer userId, String groupNumber);
    @Query("SELECT u FROM User u JOIN u.group g WHERE g.faculty = :faculty")
    List<User> getUsersByFaculty(@Param("faculty") String faculty);
}
