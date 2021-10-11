package com.grouptd.shop.repositories;

import com.grouptd.shop.entities.CustomerOrder;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderDao extends PagingAndSortingRepository<CustomerOrder,Integer> {
}
