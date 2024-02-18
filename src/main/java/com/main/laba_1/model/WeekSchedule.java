package com.main.laba_1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeekSchedule {
    ArrayList<Schedule> Понедельник = new ArrayList<>();
    ArrayList<Schedule> Вторник = new ArrayList<>();
    ArrayList<Schedule> Среда = new ArrayList<>();
    ArrayList<Schedule> Четверг = new ArrayList<>();
    ArrayList<Schedule> Пятница = new ArrayList<>();
    ArrayList<Schedule> Суббота = new ArrayList<>();
}
