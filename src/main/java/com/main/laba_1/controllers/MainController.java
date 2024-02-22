package com.main.laba_1.controllers;

import com.main.laba_1.model.Schedule;
import com.main.laba_1.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/")
public class MainController {

    static Logger logger = Logger.getLogger(MainController.class.getName());
    @GetMapping("/getsched")
    public ResponseEntity<?> getSchedule(@RequestParam(value = "groupNumber", defaultValue = "250503") String groupNumber) {
        try {
            Schedule schedule = ScheduleService.getSched(groupNumber);
            return new ResponseEntity<>(schedule, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
