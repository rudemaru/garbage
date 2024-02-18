package com.main.laba_1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Schedule {
    ArrayList<Object> weekNumber;
    ArrayList<Object> auditories;
    private String startLessonTime;
    private String endLessonTime;
    private String subject;
    private String subjectFullName;
    private boolean split;
    ArrayList<Employee> employees;
}
