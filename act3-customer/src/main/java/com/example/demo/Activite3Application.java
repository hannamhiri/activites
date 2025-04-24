package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.example.demo.entities.Customer;
import com.example.demo.repository.CustomerRepository;

@SpringBootApplication
public class Activite3Application {

	public static void main(String[] args) {
		SpringApplication.run(Activite3Application.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration) {
	restConfiguration.exposeIdsFor(Customer.class); 
		return args -> {
			Customer c1 = customerRepository.save(new Customer(null, "med", "med@gmail.com"));
			Customer c2 = customerRepository.save(new Customer(null, "amira", "amira@gmail.com"));

			System.out.println(c1.toString());
			System.out.println(c2.toString());
		};
	}
}
