package com.main.laba_1.repos;

import com.main.laba_1.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u JOIN u.group g WHERE g.faculty = :faculty")
    List<User> getUsersByFaculty(@Param("faculty") String faculty);
}
