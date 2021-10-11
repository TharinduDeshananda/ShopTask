package com.grouptd.shop.services.impl;

import com.grouptd.shop.entities.CustomUser;
import com.grouptd.shop.entities.RefreshToken;
import com.grouptd.shop.models.AuthenticationRequest;
import com.grouptd.shop.models.AuthenticationResponse;
import com.grouptd.shop.models.TokenRefreshRequest;
import com.grouptd.shop.repositories.AdminDao;
import com.grouptd.shop.repositories.CashierDao;
import com.grouptd.shop.repositories.CustomUserDao;
import com.grouptd.shop.repositories.CustomerDao;
import com.grouptd.shop.services.AuthenticationService;
import com.grouptd.shop.utils.JwtUtil;
import com.grouptd.shop.utils.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    UserDetailsService userDetailsService;

    public AuthenticationResponse authenticateUser(AuthenticationRequest authRequest)throws RuntimeException{
        String userName = authRequest.getUsername();
        String password = authRequest.getPassword();
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        if(!userDetails.isEnabled()){throw new RuntimeException("User is blocked");}

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));

        }catch(Exception e){
            throw new BadCredentialsException("Bad credentials");
        }



        CustomUser cuser = (CustomUser) userDetails;//******************************************
        String jwt = jwtUtil.generateToken(userDetails);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(cuser.getId());//****************************
        return new AuthenticationResponse(jwt,refreshToken.getRefreshTokenStr());
    }

    public AuthenticationResponse getRefreshToken(TokenRefreshRequest tokenRefreshRequest)throws Exception{
        if(tokenRefreshRequest==null||tokenRefreshRequest.getRefreshToken()==null){
            throw new Exception("invalid token refresh request");}
        String requestRefreshToken = tokenRefreshRequest.getRefreshToken();
        return refreshTokenService.findByTokenStr(requestRefreshToken)
                .map(refreshTokenService::verifyExpired)
                .map(RefreshToken::getUserName)
                .map(userName -> {
                    String token = jwtUtil.generateTokenFromUsername(userName);
                    return new AuthenticationResponse(token, requestRefreshToken);
                })
                .orElseThrow(() -> new Exception("Refresh token is not in database!"));

    }

}
