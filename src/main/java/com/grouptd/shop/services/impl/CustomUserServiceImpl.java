package com.grouptd.shop.services.impl;

import com.grouptd.shop.dtos.CustomUserDto;
import com.grouptd.shop.entities.CustomUser;
import com.grouptd.shop.repositories.CustomUserDao;
import com.grouptd.shop.services.CustomUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CustomUserServiceImpl implements CustomUserService {
    @Autowired
    CustomUserDao customUserDao;

    @Autowired
    ModelMapper modelMapper;

    public CustomUserDto findCustomUser(int userId){
        CustomUser user = customUserDao.findById(userId).get();
        return modelMapper.map(user,CustomUserDto.class);
    }

    public List<CustomUserDto> getAllCustomUsers(){
        Iterator<CustomUser> users = customUserDao.findAll().iterator();
        List<CustomUserDto> dtos = new ArrayList<>();
        while(users.hasNext()){
            dtos.add(modelMapper.map(users.next(),CustomUserDto.class));
        }
        return dtos;
    }

    public List<CustomUserDto> getAllCustomUsersPaged(int page,int pageSize){
        PageRequest request = PageRequest.of(page,pageSize);


        List<CustomUserDto> dtos = customUserDao.findAll(request).getContent().
                stream().map(user->{return modelMapper.map(user,CustomUserDto.class);}).collect(Collectors.toList());

        return dtos;
    }


}
