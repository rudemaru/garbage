package com.main.laba_1.repos;

import com.main.laba_1.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

}