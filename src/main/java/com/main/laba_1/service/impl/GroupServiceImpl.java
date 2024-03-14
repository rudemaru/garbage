package com.main.laba_1.service.impl;

import com.main.laba_1.model.GroupDto;
import com.main.laba_1.model.StudentGroupDto;
import com.main.laba_1.model.entity.Group;
import com.main.laba_1.model.entity.User;
import com.main.laba_1.repos.GroupRepository;
import com.main.laba_1.service.GroupService;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> findAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Optional<Group> findById(Integer id) {
        return groupRepository.findById(id);
    }

    @Override
    public Group findByName(String name) {
        return groupRepository.findByName(name).orElse(null);
    }

    @Override
    public Group updateGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public ResponseEntity<Boolean> deleteGroup(Integer id) {
        Group group = groupRepository.findById(id).orElse(null);
        if(group != null){
            Set<User> savedUsers = group.getSavedUsers();
            Set<User> groupUsers = group.getGroupUsers();
            for(User u : savedUsers){
                u.getSavedGroups().remove(group);
            }
            for(User u : groupUsers){
                u.setGroup(null);
            }
            groupRepository.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Set<User>> getGroupList(String name){
        Group group = groupRepository.findByName(name).orElse(null);
        if(group != null){
            Set<User> groupList = group.getGroupUsers();
            return new ResponseEntity<>(groupList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Group> addGroup(String groupNumber){
        StudentGroupDto studentGroupDto = getStudentGroupDtoObject(groupNumber);
        if(studentGroupDto != null && studentGroupDto.getGroupDto() != null) {
            GroupDto groupDto = studentGroupDto.getGroupDto();
            Group group = groupRepository.findByName(groupNumber).orElse(null);
            if (group == null) {
                group = new Group();
            }
            group.setName(groupDto.getName());
            group.setFaculty(groupDto.getFaculty());
            group.setSpeciality(groupDto.getSpeciality());
            return new ResponseEntity<>(groupRepository.save(group), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public static StudentGroupDto getStudentGroupDtoObject(@RequestParam(value = "groupNumber", defaultValue = "250503") String groupNumber){
        String template = "https://iis.bsuir.by/api/v1/schedule?studentGroup=%s";

        String url = String.format(template, groupNumber);
        WebClient webClient = WebClient.builder().build();

        StudentGroupDto studentGroupDto;
        studentGroupDto = webClient
                .get()
                .uri(url)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("client-side error")))
                .bodyToMono(StudentGroupDto.class)
                .block();

        return studentGroupDto;
    }
}