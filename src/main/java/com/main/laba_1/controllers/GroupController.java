package com.main.laba_1.controllers;

import com.main.laba_1.model.StudentGroupDto;
import com.main.laba_1.model.entity.Group;
import com.main.laba_1.service.GroupService;
import com.main.laba_1.service.impl.GroupServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class GroupController {
    private final GroupService groupService;
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/getgroups")
    public ResponseEntity<Iterable<Group>> getgroups(){
        return new ResponseEntity<>(groupService.findAllGroups(), HttpStatus.OK);
    }

    @GetMapping("/getgroup={id}")
    public Optional<Group> findEmployeeById(@PathVariable("id") Integer id) {
        return groupService.findById(id);
    }

    @PostMapping("/groups")
    public Group saveEmployee(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }

    @PutMapping("/groups")
    public Group updateEmployee(@RequestBody Group group) {
        return groupService.updateGroup(group);
    }

    @DeleteMapping("/delgroup={id}")
    public void deleteEmployee(@PathVariable("id") Integer id) {
        groupService.deleteGroup(id);
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
