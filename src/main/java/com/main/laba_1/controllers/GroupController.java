package com.main.laba_1.controllers;

import com.main.laba_1.model.dto.StudentGroupDTO;
import com.main.laba_1.model.entity.Group;
import com.main.laba_1.model.entity.User;
import com.main.laba_1.service.GroupService;
import com.main.laba_1.service.impl.GroupServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/")
public class GroupController {
    private final GroupService groupService;
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/getgroups")
    public ResponseEntity<Iterable<Group>> getAllGroups(){
        return new ResponseEntity<>(groupService.findAllGroups(), HttpStatus.OK);
    }

    @GetMapping("/getgroup={id}")
    public ResponseEntity<Group> findGroupById(@PathVariable("id") Integer id) {
        return groupService.findById(id) == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(groupService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/getgrouplist={groupNumber}")
    public ResponseEntity<Set<User>> getGroupList(@PathVariable("groupNumber") String groupNumber) {
        return groupService.getGroupList(groupNumber) == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(groupService.getGroupList(groupNumber), HttpStatus.OK);
    }

    @GetMapping("/getgroupbyname={name}")
    public ResponseEntity<Group> findGroupByName(@PathVariable("name") String name) {
        return groupService.findByName(name) == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(groupService.findByName(name), HttpStatus.OK);
    }

    @PostMapping("/addgroup={groupNumber}")
    public ResponseEntity<Group> addGroup(@PathVariable String groupNumber) {
        groupService.addGroup(groupNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/groups")
    public ResponseEntity<Group> updateGroup(@RequestBody Group group) {
        groupService.updateGroup(group);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delgroup={id}")
    public ResponseEntity<Group> deleteGroup(@PathVariable("id") Integer id) {
        groupService.deleteGroup(id);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/getgroupdto={groupNumber}")
    public ResponseEntity<StudentGroupDTO> getStudentGroupDto(@PathVariable("groupNumber") String groupNumber) {
        try {
            StudentGroupDTO studentGroupDto = GroupServiceImpl.getStudentGroupDtoObject(groupNumber);
            return new ResponseEntity<>(studentGroupDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
