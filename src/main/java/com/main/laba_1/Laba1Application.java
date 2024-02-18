package com.main.laba_1;

import com.main.laba_1.model.GroupSchedule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;


@SpringBootApplication
public class Laba1Application {

	public static void main(String[] args) {

		SpringApplication.run(Laba1Application.class, args);

		String url = "https://iis.bsuir.by/api/v1/schedule?studentGroup=250503";
		WebClient.Builder builder = WebClient.builder();

		String groupSchedule = builder.build()
				.get()
				.uri(url)
				.retrieve()
				.bodyToMono(String.class)
				.block();

		System.out.println("------------------------------------------------------");
		System.out.println(groupSchedule);
		System.out.println("------------------------------------------------------");
	}
}
