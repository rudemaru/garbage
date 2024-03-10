package com.main.laba_1.service.impl;

import com.main.laba_1.model.StudentGroupDto;
import com.main.laba_1.model.entity.Group;
import com.main.laba_1.repos.GroupRepository;
import com.main.laba_1.service.GroupService;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

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
    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group updateGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public void deleteGroup(Integer id) {
        groupRepository.deleteById(id);
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