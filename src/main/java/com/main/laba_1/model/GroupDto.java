package com.main.laba_1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("facultyAbbrev")
    private String facultyAbbrev;
    @JsonProperty("specialityName")
    private String specialityName;
    @JsonProperty("specialityAbbrev")
    private String specialityAbbrev;
    @JsonProperty("course")
    private int course;
    @JsonProperty("id")
    private int id;
}
