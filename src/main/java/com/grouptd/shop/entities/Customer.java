package com.grouptd.shop.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Customer extends CustomUser{

    private boolean status=true;
    @Temporal(value=TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date addedDate;
    @OneToOne
    private CustomUser addedBy;
    @Temporal(value=TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date modifiedDate;
    @OneToOne
    private CustomUser modifiedBy;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "customer")
    private List<CustomerOrder> customerOrders = new ArrayList<>();
    private String address;
    public Customer() {
        super();
    }

    public Customer(String fullName, String userName, String password, boolean enabled,
                    List<String> authorities, boolean status, Date addedDate,
                    Cashier addedBy, Date modifiedDate, Cashier modifiedBy) {
        super(fullName, userName, password, enabled, authorities);
        this.status = status;
        this.addedDate = addedDate;
        this.addedBy = addedBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public CustomUser getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(CustomUser addedBy) {
        this.addedBy = addedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public CustomUser getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(CustomUser modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public List<CustomerOrder> getOrders() {
        return customerOrders;
    }

    public void setOrders(List<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public List<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
