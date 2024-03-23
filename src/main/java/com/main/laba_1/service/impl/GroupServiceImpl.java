package com.main.laba_1.service.impl;

import com.main.laba_1.model.dto.GroupDTO;
import com.main.laba_1.model.dto.StudentGroupDTO;
import com.main.laba_1.model.entity.Group;
import com.main.laba_1.model.entity.User;
import com.main.laba_1.repositories.GroupRepository;
import com.main.laba_1.service.GroupService;

import java.util.*;

import com.main.laba_1.utilities.CustomCache;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Transactional
@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final CustomCache customCache;

    public GroupServiceImpl(GroupRepository groupRepository, CustomCache customCache) {
        this.groupRepository = groupRepository;
        this.customCache = customCache;
    }

    @Override
    public void saveGroup(Group group) {
        Set<User> savedUsers = group.getSavedUsers();
        Set<User> groupUsers = group.getGroupUsers();
        for(User u : savedUsers){
            customCache.updateCache(u.getId().toString(), u);
        }
        for(User u : groupUsers){
            customCache.updateCache(u.getId().toString(), u);
        }
        groupRepository.save(group);
    }

    @Override
    public List<Group> findAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group findById(Integer id) {
        return groupRepository.findById(id).orElse(null);
    }

    @Override
    public Group findByName(String name) {
        return groupRepository.findByName(name).orElse(null);
    }

    @Override
    public void updateGroup(Group group) {
        saveGroup(group);
    }

    @Override
    public void deleteGroup(Integer id) {
        Group group = groupRepository.findById(id).orElse(null);
        if(group != null){
            Set<User> savedUsers = group.getSavedUsers();
            Set<User> groupUsers = group.getGroupUsers();
            for(User u : savedUsers){
                u.getSavedGroups().remove(group);
                customCache.updateCache(u.getId().toString(), u);
            }
            for(User u : groupUsers){
                u.setGroup(null);
                customCache.updateCache(u.getId().toString(), u);
            }
            groupRepository.deleteById(id);
        }
    }

    public Set<User> getGroupList(String name){
        Group group = groupRepository.findByName(name).orElse(null);
        if(group != null){
            return group.getGroupUsers();
        }
        return Collections.emptySet();
    }

    public void addGroup(String groupNumber){
        StudentGroupDTO studentGroupDto = getStudentGroupDtoObject(groupNumber);
        if(studentGroupDto != null && studentGroupDto.getGroupDto() != null) {
            GroupDTO groupDto = studentGroupDto.getGroupDto();
            Group group = groupRepository.findByName(groupNumber).orElse(null);
            if (group == null) {
                group = new Group();
            }
            group.setName(groupDto.getName());
            group.setFaculty(groupDto.getFaculty());
            group.setSpeciality(groupDto.getSpeciality());
            saveGroup(group);
        }
    }

    public static StudentGroupDTO getStudentGroupDtoObject(@RequestParam(value = "groupNumber", defaultValue = "250503") String groupNumber){
        String template = "https://iis.bsuir.by/api/v1/schedule?studentGroup=%s";

        String url = String.format(template, groupNumber);
        WebClient webClient = WebClient.builder().build();

        StudentGroupDTO studentGroupDto;
        studentGroupDto = webClient
                .get()
                .uri(url)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("client-side error")))
                .bodyToMono(StudentGroupDTO.class)
                .block();

        return studentGroupDto;
    }
}