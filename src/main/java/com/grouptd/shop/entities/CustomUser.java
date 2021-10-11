package com.grouptd.shop.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users_table")
@Inheritance(strategy = InheritanceType.JOINED)
public class CustomUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    @NotEmpty(message = "user name should not be empty")
    private String userName;
    @NotEmpty(message = "password should not be empty")
    private String password;
    private boolean enabled=true;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_authorities",joinColumns = {@JoinColumn(name="user_id")})
    private List<String> userAuthorities = new ArrayList<>();

    public CustomUser() {
    }

    public CustomUser(String fullName, String userName, String password, boolean enabled, List<String> authorities) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
        this.userAuthorities = authorities;
    }

    public CustomUser(int id, String fullName, String userName, String password, boolean enabled, List<String> authorities) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
        this.userAuthorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (String authority:this.userAuthorities) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }

        return simpleGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setUserAuthorities(List<String> userAuthorities) {
        this.userAuthorities = userAuthorities;
    }

    public List<String> getUserAuthorities() {
        return userAuthorities;
    }
}
