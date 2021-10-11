package com.grouptd.shop.services.impl;

import com.grouptd.shop.dtos.AdminDto;
import com.grouptd.shop.entities.Admin;
import com.grouptd.shop.repositories.AdminDao;
import com.grouptd.shop.services.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public AdminDto createAdmin(Admin admin){
        admin.getUserAuthorities().add("ROLE_ADMIN");
        Admin admin1 = adminDao.save(admin);
        AdminDto dto = modelMapper.map(admin1,AdminDto.class);
        return dto;
    }

    public AdminDto updateAdmin(Admin admin){
        Admin admin1 = adminDao.save(admin);
        AdminDto dto = modelMapper.map(admin1,AdminDto.class);
        return dto;
    }

    public boolean deleteAdmin(int adminId){
        adminDao.deleteById(adminId);
        return true;
    }

    public List<AdminDto> getAllAdmins(){
        Iterable<Admin> adminIt= adminDao.findAll();
        Iterator<Admin> it = adminIt.iterator();
        List<AdminDto> dtos = new ArrayList<>();
        while(it.hasNext()){
            dtos.add(modelMapper.map(it.next(),AdminDto.class));
        }


        return dtos;
    }

    public List<AdminDto> getPagedAdmins(int page,int pageSize){
        PageRequest request = PageRequest.of(page,pageSize);
        Page<Admin> pageAdmin = adminDao.findAll(request);
        List<AdminDto> list = pageAdmin.getContent().stream().map(admin->{return modelMapper.map(admin,AdminDto.class);}).collect(Collectors.toList());

        return list;
    }



}
