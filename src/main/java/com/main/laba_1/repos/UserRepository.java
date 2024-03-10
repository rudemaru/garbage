package com.main.laba_1.repos;

import com.main.laba_1.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
