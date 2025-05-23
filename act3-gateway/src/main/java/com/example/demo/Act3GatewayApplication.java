package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Act3GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(Act3GatewayApplication.class, args);
	}

	/**@Bean 
	RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("customer-service", r -> r.path("/customers/**").uri("lb://CUSTOMER-SERVICE"))
				.route("product-service", r -> r.path("/products/**").uri("lb://PRODUCT-SERVICE"))
				.build();
	}**/
	
	@Bean
	DiscoveryClientRouteDefinitionLocator definitionLocator(
			ReactiveDiscoveryClient rdc,
			DiscoveryLocatorProperties properties) {
		return new DiscoveryClientRouteDefinitionLocator(rdc, properties);
		
	}
}
