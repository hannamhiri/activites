package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Act3EurekaDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(Act3EurekaDiscoveryApplication.class, args);
	}

}
