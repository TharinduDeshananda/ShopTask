package com.grouptd.shop.repositories;

import com.grouptd.shop.entities.CustomUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomUserDao extends PagingAndSortingRepository<CustomUser,Integer> {

    @Query("select u from CustomUser u where u.userName = :username")
    public CustomUser findByUsername(@Param("username") String username);

}
