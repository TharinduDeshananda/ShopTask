package com.grouptd.shop.repositories;

import com.grouptd.shop.entities.Admin;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao extends PagingAndSortingRepository<Admin,Integer> {
}
