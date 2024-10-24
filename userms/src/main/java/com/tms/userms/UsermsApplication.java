package com.tms.userms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UsermsApplication 
{

	public static void main(String[] args) {
		SpringApplication.run(UsermsApplication.class, args);
	}

}
