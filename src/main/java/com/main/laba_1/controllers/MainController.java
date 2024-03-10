package com.main.laba_1.controllers;

import com.main.laba_1.model.Schedule;
import com.main.laba_1.model.StudentGroupDto;
import com.main.laba_1.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class MainController {
    @GetMapping("/getsched")
    public ResponseEntity<Schedule> getSchedule(@RequestParam(value = "groupNumber", defaultValue = "250503") String groupNumber) {
        try {
            Schedule schedule = ScheduleService.getScheduleObject(groupNumber);
            return new ResponseEntity<>(schedule, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getgroupdto")
    public ResponseEntity<StudentGroupDto> getStudentGroupDto(@RequestParam(value = "groupNumber", defaultValue = "250503") String groupNumber) {
        try {
            StudentGroupDto studentGroupDto = ScheduleService.getStudentGroupDtoObject(groupNumber);
            return new ResponseEntity<>(studentGroupDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
