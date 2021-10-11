package com.grouptd.shop.utils;

import com.grouptd.shop.entities.CustomUser;
import com.grouptd.shop.entities.RefreshToken;
import com.grouptd.shop.repositories.CustomUserDao;
import com.grouptd.shop.repositories.RefreshTokenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;


@Service
public class RefreshTokenService {
    @Value("${token.refresh.expire}")
    private long expireSecLong;
    @Autowired
    private RefreshTokenDao refreshTokenDao;

    @Autowired
    CustomUserDao customUserDao;

    public Optional<RefreshToken> findByTokenStr(String token){
        return refreshTokenDao.findByRefreshTokenStr(token);
    }

    public RefreshToken createRefreshToken(int userId){
        RefreshToken token = new RefreshToken();
        token.setUserName(customUserDao.findById(userId).get().getUsername());
        token.setExpireDate(new Date(System.currentTimeMillis()+expireSecLong));
        token.setRefreshTokenStr(UUID.randomUUID().toString());

        token = refreshTokenDao.save(token);
        return token;
    }

    public RefreshToken verifyExpired(RefreshToken refreshToken) throws RuntimeException{
        if(refreshToken.getExpireDate().before(new Date())){
            refreshTokenDao.delete(refreshToken);
            throw new RuntimeException("Refresh token is expired: "+ refreshToken.getRefreshTokenStr());
        }

        return refreshToken;
    }

    @Transactional
    public int deleteByUserId(int userId){
        CustomUser user = customUserDao.findById(userId).get();
        RefreshToken token = refreshTokenDao.findByUserName(user.getUsername()).get();

        return refreshTokenDao.deleteById(token.getId());
    }

}
