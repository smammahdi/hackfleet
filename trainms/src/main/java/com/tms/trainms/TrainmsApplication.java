package com.tms.trainms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class TrainmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainmsApplication.class, args);
	}

}
