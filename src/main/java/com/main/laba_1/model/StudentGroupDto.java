package com.main.laba_1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {"employeeDto","schedules"})
public class StudentGroupDto {
    @JsonProperty("studentGroupDto")
    private GroupDto groupDto;
}
