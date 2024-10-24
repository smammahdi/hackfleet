package com.tms.bookingms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookingmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingmsApplication.class, args);
	}

}
