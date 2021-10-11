package com.grouptd.shop.services;

import com.grouptd.shop.dtos.CustomUserDto;
import com.grouptd.shop.entities.CustomUser;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public interface CustomUserService {

    public CustomUserDto findCustomUser(int userId);

    public List<CustomUserDto> getAllCustomUsers();

    public List<CustomUserDto> getAllCustomUsersPaged(int page,int pageSize);
}
