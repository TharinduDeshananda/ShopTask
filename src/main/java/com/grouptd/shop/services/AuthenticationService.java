package com.grouptd.shop.services;
import com.grouptd.shop.models.AuthenticationRequest;
import com.grouptd.shop.models.AuthenticationResponse;
import com.grouptd.shop.models.TokenRefreshRequest;


public interface AuthenticationService {

    public AuthenticationResponse authenticateUser(AuthenticationRequest authRequest)throws RuntimeException;

    public AuthenticationResponse getRefreshToken(TokenRefreshRequest tokenRefreshRequest)throws Exception;
}
