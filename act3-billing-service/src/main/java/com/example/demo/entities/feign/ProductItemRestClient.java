package com.example.demo.entities.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Product;

@FeignClient(name= "PRODUCT-SERVICE")
public interface ProductItemRestClient {

	@GetMapping(path="/products")
	PagedModel<Product> pageProducts();
	
	@GetMapping(path="/products/{id}")
	Product getProductById(@PathVariable Long id);
	
}
