package com.main.laba_1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {"employeeDto","studentGroupDto"})
public class Schedule {
    @JsonProperty("schedules")
    private WeekSchedule schedules;
}
