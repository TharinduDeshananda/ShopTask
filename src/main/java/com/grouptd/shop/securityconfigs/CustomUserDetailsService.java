package com.grouptd.shop.securityconfigs;


import com.grouptd.shop.entities.CustomUser;
import com.grouptd.shop.repositories.CustomUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    CustomUserDao customUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CustomUser customUser = customUserDao.findByUsername(username);
        if(customUser!=null){
            List<SimpleGrantedAuthority> auths = new ArrayList<>();
            for (String authority: customUser.getUserAuthorities()) {
                auths.add(new SimpleGrantedAuthority(authority));
            }
            return customUser;
        }
        else{
            throw new UsernameNotFoundException("user name not found");
        }


//        if(!username.equalsIgnoreCase("foo")){
//            throw new UsernameNotFoundException("Invalid User credentials. user name not found");
//        }
//
//        UserDetails user= User.withUsername(username).password("foo").authorities(new ArrayList<>()).build();
//
//        return user;
    }
}
