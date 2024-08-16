package com.ukg.LMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RegisteryApplication {
	public static void main(String[] args) {
		SpringApplication.run(RegisteryApplication.class, args);
	}
}
