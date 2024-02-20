package com.main.laba_1.controllers;

import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class MainController {
    Logger logger = Logger.getLogger(getClass().getName());
    @GetMapping("/getsched")

    public void getSched(@RequestParam(value = "groupNumber", defaultValue = "250503") String groupNumber) {
        String template = "https://iis.bsuir.by/api/v1/schedule?studentGroup=%s";

        String url = String.format(template, groupNumber);
        WebClient.Builder builder = WebClient.builder();

        String groupSchedule = builder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        logger.info("------------------------------------------------------");
        logger.info(groupSchedule);
        logger.info("------------------------------------------------------");
    }
}
