package com.grouptd.shop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String refreshTokenStr;
    private Date expireDate;

    public RefreshToken() {
    }

    public RefreshToken(String userName, String refreshTokenStr, Date expireDate) {
        this.userName = userName;
        this.refreshTokenStr = refreshTokenStr;
        this.expireDate = expireDate;
    }

    public RefreshToken(int id, String userName, String refreshTokenStr, Date expireDate) {
        this.id = id;
        this.userName = userName;
        this.refreshTokenStr = refreshTokenStr;
        this.expireDate = expireDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRefreshTokenStr() {
        return refreshTokenStr;
    }

    public void setRefreshTokenStr(String refreshTokenStr) {
        this.refreshTokenStr = refreshTokenStr;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
