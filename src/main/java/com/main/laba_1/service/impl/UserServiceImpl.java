package com.main.laba_1.service.impl;

import com.main.laba_1.model.entity.Group;
import com.main.laba_1.model.entity.User;
import com.main.laba_1.repositories.GroupRepository;
import com.main.laba_1.repositories.UserRepository;
import com.main.laba_1.service.UserService;
import com.main.laba_1.utilities.CustomCache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final CustomCache customCache;

    public UserServiceImpl(UserRepository userRepository, GroupRepository groupRepository, CustomCache customCache) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.customCache = customCache;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        if(customCache.containsKey(id.toString())){
            return (User)customCache.getFromCache(id.toString());
        }
        if(!customCache.containsKey(id.toString()) && userRepository.findById(id).isPresent()){
            customCache.addToCache(id.toString(), userRepository.findById(id).orElse(null));
        }
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void saveUser(User user) {
        if(customCache.containsKey(user.getId().toString())){
            customCache.updateCache(
                    user.getId().toString(), user
            );
        }
        userRepository.save(user);
    }

    @Override
    public void setUserGroup(Integer userId, String groupNumber) {
        User user = userRepository.findById(userId).orElse(null);
        Group group = groupRepository.findByName(groupNumber).orElse(null);
        if (user != null && group != null) {
            user.setGroup(group);
            saveUser(user);
        }
    }

    @Override
    public void deleteUserGroup(Integer userId){
        User user = userRepository.findById(userId).orElse(null);
        if(user != null){
            user.setGroup(null);
            saveUser(user);
        }
    }

    @Override
    public List<User> getUsersByFaculty(String faculty) {
        return userRepository.getUsersByFaculty(faculty);
    }

    @Override
    public void userSaveGroup(Integer userId, String groupNumber) {

        User user = userRepository.findById(userId).orElse(null);
        Group group = groupRepository.findByName(groupNumber).orElse(null);

        if(user != null && group != null){
            user.getSavedGroups().add(group);
            saveUser(user);
        }
    }

    @Override
    public void deleteSavedGroup(Integer userId, String groupNumber){

        User user = userRepository.findById(userId).orElse(null);
        Group group = groupRepository.findByName(groupNumber).orElse(null);

        if(user != null && group != null && user.getSavedGroups().contains(group)){
            user.getSavedGroups().remove(group);
            saveUser(user);
        }
    }

    @Override
    public void updateUser(User user) {
        saveUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        boolean resp = user != null;
        if(resp){
            userRepository.deleteById(id);
        }
    }
}
