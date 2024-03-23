package com.main.laba_1.service;

import com.main.laba_1.model.entity.Group;
import com.main.laba_1.model.entity.User;

import java.util.List;
import java.util.Set;

public interface GroupService {
    List<Group> findAllGroups();
    void saveGroup(Group group);
    Group findById(Integer id);
    Group findByName(String name);
    void updateGroup(Group group);
    void deleteGroup(Integer id);
    Set<User> getGroupList(String name);
    void addGroup(String groupNumber);
}