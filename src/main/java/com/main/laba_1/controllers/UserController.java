package com.main.laba_1.controllers;

import com.main.laba_1.model.entity.User;
import com.main.laba_1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getusers")
    public ResponseEntity<Iterable<User>> getUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/getuser={id}")
    public Optional<User> findUserById(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PatchMapping("/assigngroup/{userId}/{groupNumber}")
    public ResponseEntity<User> addUserToGroup(@PathVariable Integer userId, @PathVariable String groupNumber){
        return userService.setUserGroup(userId, groupNumber);
    }

    @PutMapping("/savegroup/{userId}/{groupNumber}")
    public ResponseEntity<User> userSaveGroup(@PathVariable Integer userId, @PathVariable String groupNumber){
        return userService.userSaveGroup(userId, groupNumber);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/deleteusergroup/{userId}")
    public ResponseEntity<User> deleteUserGroup(@PathVariable Integer userId){
        return userService.deleteUserGroup(userId);
    }

    @DeleteMapping("/deletesavedgroup/{userId}/{groupNumber}")
    public ResponseEntity<User> deleteSavedGroup(@PathVariable Integer userId, @PathVariable String groupNumber){
        return userService.deleteSavedGroup(userId, groupNumber);
    }

    @DeleteMapping("/deluser={id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Integer id) {
        return userService.deleteUser(id);
    }
}
