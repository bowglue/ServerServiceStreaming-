package com.serverapp.trailerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TrailerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrailerServiceApplication.class, args);
	}

}
