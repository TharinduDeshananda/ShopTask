package com.grouptd.shop.controllers;

import com.grouptd.shop.entities.*;
import com.grouptd.shop.models.AuthenticationRequest;
import com.grouptd.shop.models.ErrorResponse;
import com.grouptd.shop.models.TokenRefreshRequest;
import com.grouptd.shop.repositories.AdminDao;
import com.grouptd.shop.repositories.CashierDao;
import com.grouptd.shop.repositories.CustomUserDao;
import com.grouptd.shop.repositories.CustomerDao;
import com.grouptd.shop.services.AuthenticationService;
import com.grouptd.shop.services.impl.AuthenticationServiceImpl;
import com.grouptd.shop.utils.JwtUtil;
import com.grouptd.shop.utils.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    AuthenticationService authService;

    @RequestMapping("/authenticate")
    public ResponseEntity authenticate(@Valid @RequestBody AuthenticationRequest authRequest, Errors errors){
        if(errors.hasErrors()){
            ResponseEntity.ok(new ErrorResponse("User Name and Password cannot be empty"));
        }
        return ResponseEntity.ok(authService.authenticateUser(authRequest));

    }

    @PostMapping("/refreshtoken")
    public ResponseEntity refreshToken(@RequestBody TokenRefreshRequest tokenRefreshRequest)throws Exception{
        return ResponseEntity.ok(authService.getRefreshToken(tokenRefreshRequest));

    }


}
