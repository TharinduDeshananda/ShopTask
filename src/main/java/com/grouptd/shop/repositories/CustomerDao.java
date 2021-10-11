package com.grouptd.shop.repositories;

import com.grouptd.shop.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao extends PagingAndSortingRepository<Customer,Integer> {
    @Query("select c from Customer c where c.status = true")
    public Page<Customer> findActiveCustomers(Pageable pageRequest);

    @Query("select c from Customer c where c.status = true")
    public List<Customer> findActiveCustomers();
}
