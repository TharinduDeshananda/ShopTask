package com.grouptd.shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDto {

    private String fullName;
    private String userName;
    private boolean enabled=true;
    private List<String> userAuthorities = new ArrayList<>();


}
