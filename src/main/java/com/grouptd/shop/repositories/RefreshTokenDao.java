package com.grouptd.shop.repositories;

import com.grouptd.shop.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface RefreshTokenDao extends JpaRepository<RefreshToken,Integer> {

    public Optional<RefreshToken> findByUserName(String userName);
    public Optional<RefreshToken> findByRefreshTokenStr(String refreshTokenStr);
    public int deleteById(int id);
}
