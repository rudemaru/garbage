package com.main.laba_1.controllers;

import com.main.laba_1.model.entity.User;
import com.main.laba_1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/")
public class UserController {
    private final UserService userService;
    Logger logger = Logger.getLogger(getClass().getName());

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getusers")
    public ResponseEntity<Iterable<User>> getUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/getuser={id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Integer id) {
        long startTime = System.nanoTime();
        ResponseEntity<User> response = userService.findById(id) == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
        long endTime = System.nanoTime();
        String log = String.format("EXECUTION TIME: %.0f ms", ((double)endTime - (double)startTime)/1000000);
        logger.info(log);
        return response;
    }

    @GetMapping("/getusersbyfaculty={faculty}")
    public ResponseEntity<List<User>> getUsersByFaculty(@PathVariable("faculty") String faculty){
        return userService.getUsersByFaculty(faculty) == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(userService.getUsersByFaculty(faculty), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/assigngroup/{userId}/{groupNumber}")
    public ResponseEntity<User> addUserToGroup(@PathVariable Integer userId, @PathVariable String groupNumber){
        userService.setUserGroup(userId, groupNumber);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/savegroup/{userId}/{groupNumber}")
    public ResponseEntity<User> userSaveGroup(@PathVariable Integer userId, @PathVariable String groupNumber){
        userService.userSaveGroup(userId, groupNumber);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteusergroup/{userId}")
    public ResponseEntity<User> deleteUserGroup(@PathVariable Integer userId){
        userService.deleteUserGroup(userId);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deletesavedgroup/{userId}/{groupNumber}")
    public ResponseEntity<User> deleteSavedGroup(@PathVariable Integer userId, @PathVariable String groupNumber){
        userService.deleteSavedGroup(userId, groupNumber);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deluser={id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
