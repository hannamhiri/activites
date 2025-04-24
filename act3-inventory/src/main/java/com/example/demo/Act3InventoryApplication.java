package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;

@SpringBootApplication
public class Act3InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(Act3InventoryApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration) {
			restConfiguration.exposeIdsFor(Product.class); 
		return args -> {
			Product p1 = productRepository.save(new Product(null, "ordinateur", 788,12));
			Product p2 = productRepository.save(new Product(null, "imprimante", 88,129));

			System.out.println(p1.getName());
			System.out.println(p2.getName());
		};
	}
	

}
