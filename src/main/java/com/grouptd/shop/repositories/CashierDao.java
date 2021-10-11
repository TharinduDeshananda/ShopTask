package com.grouptd.shop.repositories;

import com.grouptd.shop.entities.Cashier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashierDao extends PagingAndSortingRepository<Cashier,Integer> {

    @Query("select c from Cashier c where c.status = true")
    public Page<Cashier> findActiveCashiers(Pageable pageRequest);

    @Query("select c from Cashier c where c.status = true")
    public List<Cashier> findActiveCashiers();
}
