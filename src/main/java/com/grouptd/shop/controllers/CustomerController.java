package com.grouptd.shop.controllers;

import com.grouptd.shop.dtos.CustomerDto;
import com.grouptd.shop.entities.Customer;
import com.grouptd.shop.entities.CustomerOrder;
import com.grouptd.shop.models.ErrorResponse;
import com.grouptd.shop.services.CustomerService;
import com.grouptd.shop.services.impl.CustomerServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity createCustomer(@Valid @RequestBody Customer customer, Errors errors){
        if(errors.hasErrors()){
            ResponseEntity.ok(new ErrorResponse("User Name and Password cannot be empty"));
        }
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @PutMapping("/")
    public ResponseEntity updateCustomer(@Valid @RequestBody Customer customer,Errors errors){
        if(errors.hasErrors()){
            ResponseEntity.ok(new ErrorResponse("User Name and Password cannot be empty"));
        }
        return ResponseEntity.ok(customerService.updateCustomer(customer));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable("customerId") int id){


        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }

    @GetMapping("/")
    public ResponseEntity getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{page}/{pageSize}")
    public ResponseEntity getAllPaged(@PathVariable("page")int page,@PathVariable("pageSize")int pageSize){

        return ResponseEntity.ok(customerService.getAllPaged(page,pageSize));
    }

    @GetMapping("/activeCustomers")
    public ResponseEntity getActiveCustomers(){

        return ResponseEntity.ok(customerService.getActiveCustomers());

    }

    @GetMapping("/activeCustomers/{page}/{pageSize}")
    public ResponseEntity getActiveCustomersPaged(@PathVariable("path")int page,@PathVariable("pageSize")int pageSize){

        return ResponseEntity.ok(customerService.getActiveCustomersPaged(page,pageSize));

    }

    @GetMapping("/getOrders/{customerId}")
    public ResponseEntity getOrdersOfCustomer(@PathVariable("customerId")int id){
        return ResponseEntity.ok(customerService.getCustomerOrders(id));
    }

    @PostMapping("/placeOrder/{customerId}")
    public ResponseEntity placeCustomerOrder(@RequestBody CustomerOrder order,@PathVariable("customerId")int id){
        return ResponseEntity.ok(customerService.placeAnOrder(id,order));
    }

    @DeleteMapping("/removeOrder/{customerId}/{orderId}")
    public ResponseEntity removeOrderFromCustomer(@PathVariable("customerId")int customerId,@PathVariable("orderId")int orderId){
        Customer customer = customerService.removeOrder(customerId,orderId);
        return ResponseEntity.ok(modelMapper.map(customer,CustomerDto.class));
    }

}
