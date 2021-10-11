package com.grouptd.shop.models;

public class RefreshTokenResponse {
    private String jtw;
    private String refreshTokenStr;

    public RefreshTokenResponse() {
    }

    public RefreshTokenResponse(String jtw, String refreshTokenStr) {
        this.jtw = jtw;
        this.refreshTokenStr = refreshTokenStr;
    }

    public String getJtw() {
        return jtw;
    }

    public void setJtw(String jtw) {
        this.jtw = jtw;
    }

    public String getRefreshTokenStr() {
        return refreshTokenStr;
    }

    public void setRefreshTokenStr(String refreshTokenStr) {
        this.refreshTokenStr = refreshTokenStr;
    }
}
