package com.grouptd.shop.services;

import com.grouptd.shop.dtos.AdminDto;
import com.grouptd.shop.entities.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


public interface AdminService {

    public AdminDto createAdmin(Admin admin);

    public AdminDto updateAdmin(Admin admin);

    public boolean deleteAdmin(int adminId);

    public List<AdminDto> getAllAdmins();

    public List<AdminDto> getPagedAdmins(int page,int pageSize);



}
