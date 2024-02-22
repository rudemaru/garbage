package com.main.laba_1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeekSchedule {
    @JsonProperty("Понедельник")
    private ArrayList<DaySchedule> monday;
    @JsonProperty("Вторник")
    private ArrayList<DaySchedule> tuesday;
    @JsonProperty("Среда")
    private ArrayList<DaySchedule> wednesday;
    @JsonProperty("Четверг")
    private ArrayList<DaySchedule> thursday;
    @JsonProperty("Пятница")
    private ArrayList<DaySchedule> friday;
    @JsonProperty("Суббота")
    private ArrayList<DaySchedule> saturday;
}