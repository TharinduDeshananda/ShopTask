package com.grouptd.shop.services;

import com.grouptd.shop.dtos.CustomOrderDto;
import com.grouptd.shop.dtos.CustomerDto;
import com.grouptd.shop.entities.CustomUser;
import com.grouptd.shop.entities.Customer;
import com.grouptd.shop.entities.CustomerOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public interface CustomerService {


    public CustomerDto createCustomer(Customer customer);
    public CustomerDto updateCustomer(Customer customer);
    public boolean deleteCustomer(int id);
    public List<CustomerDto> getAllCustomers();
    public List<CustomerDto> getAllPaged(int page,int pageSize);


    public List<CustomerDto> getActiveCustomers();
    public List<CustomerDto> getActiveCustomersPaged(int page,int pageSize);

    public CustomerDto placeAnOrder(int customerId, CustomerOrder order);

    public CustomerDto setCustomerStatus(int id,boolean status);

    public List<CustomOrderDto> getCustomerOrders(int customerId);

    public Customer removeOrder(int customerId,int orderId);
}
