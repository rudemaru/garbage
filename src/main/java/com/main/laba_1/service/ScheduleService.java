package com.main.laba_1.service;

import com.main.laba_1.model.Schedule;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Service
public class ScheduleService {

    private ScheduleService() {
        throw new IllegalStateException("Utility class");
    }
    static Logger logger = Logger.getLogger(ScheduleService.class.getName());
    public static Schedule getScheduleObject(@RequestParam(value = "groupNumber", defaultValue = "250503") String groupNumber){
        String template = "https://iis.bsuir.by/api/v1/schedule?studentGroup=%s";

        String url = String.format(template, groupNumber);
        WebClient webClient = WebClient.builder().build();

        Schedule groupSchedule;
            groupSchedule = webClient
                    .get()
                    .uri(url)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,
                            error -> Mono.error(new RuntimeException("CLIENT ERROR")))
                    .bodyToMono(Schedule.class)
                    .block();

        if(groupSchedule != null && groupSchedule.toString() != null) {
            logger.info("------------------------------------------------------");
            String parsedObject = "OBJECT: " + groupSchedule;
            logger.info(parsedObject);
            logger.info("------------------------------------------------------");
        }

        return groupSchedule;
    }
}
