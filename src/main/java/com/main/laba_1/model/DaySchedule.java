package com.main.laba_1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DaySchedule {
    @JsonProperty("weekNumber")
    public List<Integer> weekNumber;
    @JsonProperty("numSubgroup")
    public Integer numSubgroup;
    @JsonProperty("auditories")
    public List<String> auditories;
    @JsonProperty("startLessonTime")
    public String startLessonTime;
    @JsonProperty("endLessonTime")
    public String endLessonTime;
    @JsonProperty("subject")
    public String subject;
    @JsonProperty("subjectFullName")
    public String subjectFullName;
    @JsonProperty("split")
    public Boolean split;
}
