package com.main.laba_1.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {"employeeDto","schedules"})
public class StudentGroupDTO {
    @JsonProperty("studentGroupDto")
    private GroupDTO groupDto;
}
