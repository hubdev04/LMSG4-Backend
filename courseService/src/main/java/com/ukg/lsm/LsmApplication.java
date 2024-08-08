package com.ukg.lsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.sql.DataSource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LsmApplication {

	public static void main(String[] args) {
		SpringApplication.run(LsmApplication.class, args);
		System.out.println("Hello World");
	}

}
