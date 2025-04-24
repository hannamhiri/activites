package com.example.demo.entities;

import com.example.demo.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class ProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double quantity;
    private double price;
    private long productID;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Bill bill;

    @Transient
    private Product product;
    
    @Transient
    private String productName;

    // Constructeur vide
    public ProductItem() {
    }

    // Constructeur avec tous les champs
    public ProductItem(Long id, double quantity, double price, long productID, Bill bill, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.productID = productID;
        this.bill = bill;
        this.product = product;
    }

    // Getters et Setters
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;  // Assurez-vous d'avoir cette méthode
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // toString
    @Override
    public String toString() {
        return "ProductItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", productID=" + productID +
                ", bill=" + bill +
                ", product=" + product +
                '}';
    }
}
