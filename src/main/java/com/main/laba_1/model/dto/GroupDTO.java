package com.main.laba_1.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("facultyAbbrev")
    private String faculty;
    @JsonProperty("specialityName")
    private String speciality;
}
