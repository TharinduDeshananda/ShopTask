package com.grouptd.shop.entities;

import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@ToString
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price;
    private int itemCount;
    private String description;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne
    private Customer customer;

    public CustomerOrder() {
    }

    public CustomerOrder(double price, int itemCount,Date date,String description) {
        this.price = price;
        this.itemCount = itemCount;
        this.date = date;
        this.description = description;
    }

    public CustomerOrder(int id, double price, int itemCount,Date date,String description) {
        this.id = id;
        this.price = price;
        this.itemCount = itemCount;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
