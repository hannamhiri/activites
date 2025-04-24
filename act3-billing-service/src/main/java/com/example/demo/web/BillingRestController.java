package com.example.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Bill;
import com.example.demo.entities.feign.CustomerRestClient;
import com.example.demo.entities.feign.ProductItemRestClient;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.repository.BillRepository;
import com.example.demo.repository.ProductItemRepository;

@RestController
public class BillingRestController{
	
	private BillRepository billRepository;
	private  ProductItemRepository productItemRepository;
	private CustomerRestClient customerResClient;
	private ProductItemRestClient productItemRestClient;
	
	
	public BillingRestController(BillRepository billRepository, ProductItemRepository productItemRepository,
			CustomerRestClient customerResClient, ProductItemRestClient productItemRestClient) {
		super();
		this.billRepository = billRepository;
		this.productItemRepository = productItemRepository;
		this.customerResClient = customerResClient;
		this.productItemRestClient = productItemRestClient;
	}
	
	
	@GetMapping(path = "/fullBill/{id}")
	public Bill getBill(@PathVariable(name ="id") Long id) {
		Bill bill = billRepository.findById(id).get();
		Customer customer = customerResClient.getCustomerById(bill.getCustomerID());
		bill.setCustomer(customer);
		
		bill.getProductItems().forEach(pi -> {
		Product product = productItemRestClient.getProductById(pi.getProductID());
		//pi.setProduct(product);
		pi.setProductName(product.getName());
	});
		return bill;
}
}
