package com.grouptd.shop.controllers;

import com.grouptd.shop.entities.Admin;
import com.grouptd.shop.models.ErrorResponse;
import com.grouptd.shop.services.AdminService;
import com.grouptd.shop.services.impl.AdminServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity createAdmin(@Valid @RequestBody Admin admin, BindingResult errors){
        if(errors.hasErrors()){
            ResponseEntity.ok(new ErrorResponse("User Name and Password cannot be empty"));
        }
        return ResponseEntity.ok(adminService.createAdmin(admin));
    }

    @PutMapping("/")
    public ResponseEntity updateAdmin(@Valid @RequestBody Admin admin,Errors errors){
        if(errors.hasErrors()){
            ResponseEntity.ok(new ErrorResponse("User Name and Password cannot be empty"));
        }
        return ResponseEntity.ok(adminService.updateAdmin(admin));
    }

    @DeleteMapping("/{adminId}")
    public ResponseEntity deleteAdmin(@PathVariable("adminId")int adminId){

        return ResponseEntity.ok(adminService.deleteAdmin(adminId));
    }

    @GetMapping("/")
    public ResponseEntity getAllAdmins(){
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    @GetMapping("/{page}/{pageSize}")
    public ResponseEntity getPagedAdmins(@PathVariable("page")int page,@PathVariable("pageSize")int pageSize){
        return ResponseEntity.ok(adminService.getPagedAdmins(page,pageSize));
    }

    @RequestMapping("/getAdmindetails")
    public String getAdminDetails(){

        return "this is admin details";
    }

}
