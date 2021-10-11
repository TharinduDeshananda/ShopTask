package com.grouptd.shop.services.impl;

import com.grouptd.shop.dtos.CustomOrderDto;
import com.grouptd.shop.dtos.CustomerDto;
import com.grouptd.shop.entities.CustomUser;
import com.grouptd.shop.entities.Customer;
import com.grouptd.shop.entities.CustomerOrder;
import com.grouptd.shop.repositories.CustomerDao;
import com.grouptd.shop.repositories.CustomerOrderDao;
import com.grouptd.shop.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    CustomerOrderDao customerOrderDao;

    @Autowired
    ModelMapper modelMapper;

    public CustomerDto createCustomer(Customer customer){
        CustomUser creator = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        customer.getUserAuthorities().add("ROLE_CUSTOMER");
        customer.setAddedBy(creator);
        customer.setModifiedBy(creator);
        Customer customer1 = customerDao.save(customer);
        return modelMapper.map(customer, CustomerDto.class);
    }
    public CustomerDto updateCustomer(Customer customer){
        CustomUser creator = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        customer.setModifiedBy(creator);
        Customer customer1 = customerDao.save(customer);
        return modelMapper.map(customer1,CustomerDto.class);
    }
    public boolean deleteCustomer(int id){
        customerDao.deleteById(id);

        return true;
    }
    public List<CustomerDto> getAllCustomers(){

        Iterable<Customer> customerIterable = customerDao.findAll();
        Iterator<Customer> customerIterator = customerIterable.iterator();
        List<CustomerDto> dtos = new ArrayList<>();
        while(customerIterator.hasNext()){
            dtos.add(modelMapper.map(customerIterator.next(),CustomerDto.class));
        }

        return dtos;
    }
    public List<CustomerDto> getAllPaged(int page,int pageSize){
        PageRequest request = PageRequest.of(page,pageSize);
        Page<Customer> customers = customerDao.findAll(request);
        return customers.getContent()
                .stream().map(customer -> {return modelMapper.map(customer,CustomerDto.class);})
                .collect(Collectors.toList());
    }


    public List<CustomerDto> getActiveCustomers(){
        List<Customer> activeCustomers = customerDao.findActiveCustomers();
        return activeCustomers.stream().map(customer -> {return modelMapper.map(customer,CustomerDto.class);})
                .collect(Collectors.toList());

    }
    public List<CustomerDto> getActiveCustomersPaged(int page,int pageSize){
        Page<Customer> activeCustomers = customerDao.findActiveCustomers(PageRequest.of(page,pageSize));
        return activeCustomers.getContent().stream().map(customer -> {return modelMapper.map(customer,CustomerDto.class);})
                .collect(Collectors.toList());

    }

    public CustomerDto placeAnOrder(int customerId,CustomerOrder order){
        Customer customer = customerDao.findById(customerId).get();
        if(customer==null)return null;
        order.setCustomer(customer);
        customer.getOrders().add(order);
        customer = customerDao.save(customer);
        return modelMapper.map(customer,CustomerDto.class);
    }

    public CustomerDto setCustomerStatus(int id,boolean status){
        Customer customer = customerDao.findById(id).get();
        customer.setStatus(status);
        customer.setEnabled(status);
        return modelMapper.map(customer,CustomerDto.class);
    }

    public List<CustomOrderDto> getCustomerOrders(int customerId){
        Customer customer = customerDao.findById(customerId).get();
        List<CustomOrderDto> orders = customer.getOrders().stream().map(order->{return modelMapper.map(order,CustomOrderDto.class);}).collect(Collectors.toList());
        return orders;
    }

    public Customer removeOrder(int customerId,int orderId){
        Customer customer = customerDao.findById(customerId).get();
        CustomerOrder order = customerOrderDao.findById(orderId).get();
        customer.getOrders().stream().filter(order1 -> order1.getId()!=orderId);
        customer = customerDao.save(customer);
        customerOrderDao.deleteById(orderId);
        return customer;
    }
}
