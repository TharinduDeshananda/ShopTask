package com.grouptd.shop.models;

public class AuthenticationResponse {

    private String jwt;
    private String refreshTokenStr;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String jwt, String refreshTokenStr) {
        this.jwt = jwt;
        this.refreshTokenStr = refreshTokenStr;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getRefreshTokenStr() {
        return refreshTokenStr;
    }

    public void setRefreshTokenStr(String refreshTokenStr) {
        this.refreshTokenStr = refreshTokenStr;
    }
}
