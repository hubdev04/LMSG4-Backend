package com.ukg.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		System.out.println("\n\n  api gateway running \n\n");
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
}
