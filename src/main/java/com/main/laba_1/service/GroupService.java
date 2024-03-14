package com.main.laba_1.service;

import com.main.laba_1.model.entity.Group;
import com.main.laba_1.model.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface GroupService {
    List<Group> findAllGroups();
    Optional<Group> findById(Integer id);
    Group findByName(String name);
    Group saveGroup(Group group);
    Group updateGroup(Group group);
    void deleteGroup(Integer id);
    ResponseEntity<Set<User>> getGroupList(String name);
}