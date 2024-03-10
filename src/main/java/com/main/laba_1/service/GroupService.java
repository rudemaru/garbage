package com.main.laba_1.service;

import com.main.laba_1.model.entity.Group;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    List<Group> findAllGroups();
    Optional<Group> findById(Integer id);
    Group saveGroup(Group group);
    Group updateGroup(Group group);
    void deleteGroup(Integer id);
}