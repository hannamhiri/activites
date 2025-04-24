package com.example.demo.entities;

import java.util.Collection;
import java.util.Date;

import com.example.demo.model.Customer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date billingDate;

    @OneToMany(mappedBy = "bill")
    private Collection<ProductItem> productItems;

    private long customerID;

    @Transient
    private Customer customer;

    // Constructeur vide
    public Bill() {
    }

    // Constructeur avec tous les champs
    public Bill(Long id, Date billingDate, Collection<ProductItem> productItems, long customerID, Customer customer) {
        this.id = id;
        this.billingDate = billingDate;
        this.productItems = productItems;
        this.customerID = customerID;
        this.customer = customer;
    }

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    public Collection<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(Collection<ProductItem> productItems) {
        this.productItems = productItems;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // toString
    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", billingDate=" + billingDate +
                ", productItems=" + productItems +
                ", customerID=" + customerID +
                ", customer=" + customer +
                '}';
    }
    public void addProductItem(ProductItem item) {
        productItems.add(item);
        item.setBill(this);
    }
}
