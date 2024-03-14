package com.main.laba_1.service.impl;

import com.main.laba_1.model.entity.Group;
import com.main.laba_1.model.entity.User;
import com.main.laba_1.repos.GroupRepository;
import com.main.laba_1.repos.UserRepository;
import com.main.laba_1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public UserServiceImpl(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    @Transactional
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public ResponseEntity<User> setUserGroup(Integer userId, String groupNumber) {

        User user = userRepository.findById(userId).orElse(null);
        Group group = groupRepository.findByName(groupNumber).orElse(null);

        if (user != null && group != null) {
            user.setGroup(group);
            userRepository.save(user);

            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public ResponseEntity<User> deleteUserGroup(Integer userId){

        User user = userRepository.findById(userId).orElse(null);

        if(user != null){
            user.setGroup(null);
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public ResponseEntity<User> userSaveGroup(Integer userId, String groupNumber) {

        User user = userRepository.findById(userId).orElse(null);
        Group group = groupRepository.findByName(groupNumber).orElse(null);

        if(user != null && group != null){
            user.getSavedGroups().add(group);
            userRepository.save(user);

            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public ResponseEntity<User> deleteSavedGroup(Integer userId, String groupNumber){

        User user = userRepository.findById(userId).orElse(null);
        Group group = groupRepository.findByName(groupNumber).orElse(null);

        if(user != null && group != null && user.getSavedGroups().contains(group)){
            user.getSavedGroups().remove(group);
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public ResponseEntity<Boolean> deleteUser(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        boolean resp = false;
        if(user != null){
            userRepository.deleteById(id);
            resp = true;
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
}
