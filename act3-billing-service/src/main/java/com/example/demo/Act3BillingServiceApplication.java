package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.PagedModel;

import com.example.demo.entities.Bill;
import com.example.demo.entities.ProductItem;
import com.example.demo.entities.feign.CustomerRestClient;
import com.example.demo.entities.feign.ProductItemRestClient;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.repository.BillRepository;
import com.example.demo.repository.ProductItemRepository;

@SpringBootApplication
@EnableFeignClients
public class Act3BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Act3BillingServiceApplication.class, args);
    }

   
   
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductItemRestClient productItemRestClient) {
        return args -> {
            // Get customer by id
            Customer customer = customerRestClient.getCustomerById(1L);

            // Create new bill and save it
            Bill bill1 = billRepository.save(new Bill(null, new Date(), null, customer.getId(), null));

            // Get paged products from productItemRestClient
            PagedModel<Product> productPagedModel = productItemRestClient.pageProducts();

            // Access the content (List<Product>) from PagedModel
            List<Product> products = productPagedModel.getContent();

            // Iterate over the products and create ProductItem for each
            products.forEach(p -> {
                ProductItem productItem = new ProductItem();
                productItem.setPrice(p.getPrice());
                productItem.setQuantity(1 + new Random().nextInt(100));
                productItem.setProductID(p.getId());
                productItem.setBill(bill1); // Définit le côté "many"
                
                // Initialise la collection si elle est null
                if (bill1.getProductItems() == null) {
                    bill1.setProductItems(new ArrayList<>());
                }
                bill1.getProductItems().add(productItem); // Définit le côté "one"
                
                productItemRepository.save(productItem);
            });

        };
    }
}
