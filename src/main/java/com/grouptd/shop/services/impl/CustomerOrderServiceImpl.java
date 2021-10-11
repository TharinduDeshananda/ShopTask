package com.grouptd.shop.services.impl;

import com.grouptd.shop.dtos.CustomOrderDto;
import com.grouptd.shop.entities.CustomerOrder;
import com.grouptd.shop.repositories.CustomerOrderDao;
import com.grouptd.shop.services.CustomerOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderDao customerOrderDao;

    @Autowired
    private ModelMapper modelMapper;



    public List<CustomOrderDto> getAllOrders(){
        Iterable<CustomerOrder> orders = customerOrderDao.findAll();
        Iterator<CustomerOrder> it = orders.iterator();
        List<CustomOrderDto> orderList = new ArrayList<>();
        while(it.hasNext()){
            orderList.add(modelMapper.map(it.next(),CustomOrderDto.class));
        }

        return orderList;
    }

    public List<CustomOrderDto> getPagedOrders(int page,int pageSize){
        PageRequest pageRequest = PageRequest.of(page,pageSize);
        return customerOrderDao.findAll(pageRequest).getContent().
                stream().map(corder->{return modelMapper.map(corder,CustomOrderDto.class);}).
                collect(Collectors.toList());
    }

    public boolean removeCustomerOrder(int orderId){
        customerOrderDao.deleteById(orderId);
        return true;
    }
}
