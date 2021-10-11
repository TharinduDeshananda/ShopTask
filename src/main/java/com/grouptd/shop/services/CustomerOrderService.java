package com.grouptd.shop.services;

import com.grouptd.shop.dtos.CustomOrderDto;
import com.grouptd.shop.entities.CustomerOrder;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public interface CustomerOrderService {

    public List<CustomOrderDto> getAllOrders();

    public List<CustomOrderDto> getPagedOrders(int page,int pageSize);

    public boolean removeCustomerOrder(int orderId);
}
