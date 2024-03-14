package com.main.laba_1.controllers;

import com.main.laba_1.model.StudentGroupDto;
import com.main.laba_1.model.entity.Group;
import com.main.laba_1.model.entity.User;
import com.main.laba_1.service.GroupService;
import com.main.laba_1.service.impl.GroupServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
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
    public Optional<Group> findGroupById(@PathVariable("id") Integer id) {
        return groupService.findById(id);
    }

    @GetMapping("/getgrouplist={groupNumber}")
    public ResponseEntity<Set<User>> getGroupList(@PathVariable("groupNumber") String groupNumber) {
        return groupService.getGroupList(groupNumber);
    }

    @GetMapping("/getgroupbyname={name}")
    public Group findGroupByName(@PathVariable("name") String name) {
        return groupService.findByName(name);
    }

    @PostMapping("/addgroup={groupNumber}")
    public ResponseEntity<Group> addGroup(@PathVariable String groupNumber) {
        return groupService.addGroup(groupNumber);
    }

    @PutMapping("/groups")
    public Group updateGroup(@RequestBody Group group) {
        return groupService.updateGroup(group);
    }

    @DeleteMapping("/delgroup={id}")
    public ResponseEntity<Boolean> deleteGroup(@PathVariable("id") Integer id) {
        return groupService.deleteGroup(id);
    }
    
    @GetMapping("/getgroupdto={groupNumber}")
    public ResponseEntity<StudentGroupDto> getStudentGroupDto(@PathVariable("groupNumber") String groupNumber) {
        try {
            StudentGroupDto studentGroupDto = GroupServiceImpl.getStudentGroupDtoObject(groupNumber);
            return new ResponseEntity<>(studentGroupDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
