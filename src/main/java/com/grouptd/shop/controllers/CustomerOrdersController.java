package com.grouptd.shop.controllers;

import com.grouptd.shop.services.CustomerOrderService;
import com.grouptd.shop.services.impl.CustomerOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customerorders")
public class CustomerOrdersController {
    @Autowired
    private CustomerOrderService customerOrderService;

    @GetMapping("/")
    public ResponseEntity getAllOrders(){

        return ResponseEntity.ok(customerOrderService.getAllOrders());

    }

    @GetMapping("/{page}/{pageSize}")
    public ResponseEntity getPagedOrders(@PathVariable("page")int page,@PathVariable("pageSize") int pageSize){
        return ResponseEntity.ok(customerOrderService.getPagedOrders(page,pageSize));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity removeCustomerOrder(@PathVariable("orderId")int orderId){

        return ResponseEntity.ok(customerOrderService.removeCustomerOrder(orderId));
    }
}
