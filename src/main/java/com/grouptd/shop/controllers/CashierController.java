package com.grouptd.shop.controllers;

import com.grouptd.shop.entities.Cashier;
import com.grouptd.shop.models.ErrorResponse;
import com.grouptd.shop.services.CashierService;
import com.grouptd.shop.services.impl.CashierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cashiers")
public class CashierController {

    @Autowired
    CashierService cashierService;

    @PostMapping("/")
    public ResponseEntity createCashier(@Valid @RequestBody Cashier cashier, Errors errors){
        if(errors.hasErrors()){
            ResponseEntity.ok(new ErrorResponse("User Name and Password cannot be empty"));
        }
        return ResponseEntity.ok(cashierService.createCashier(cashier));
    }

    @PutMapping("/")
    public ResponseEntity updateCustomer(@Valid @RequestBody Cashier cashier,Errors errors){
        if(errors.hasErrors()){
            ResponseEntity.ok(new ErrorResponse("User Name and Password cannot be empty"));
        }
        return ResponseEntity.ok(cashierService.updateCasier(cashier));
    }

    @DeleteMapping("/{cashierId}")
    public ResponseEntity deleteCashier(@PathVariable("cashierId")int id){

        return ResponseEntity.ok(cashierService.deleteCashier(id));
    }

    @GetMapping("/")
    public ResponseEntity getAllCashiers(){


        return ResponseEntity.ok(cashierService.getAllCashiers());
    }

    @GetMapping("/{page}/{pageSize}")
    public ResponseEntity getPagedCashiers(@PathVariable("page")int page,@PathVariable("pageSize")int pageSize){

        return ResponseEntity.ok(cashierService.getActiveCashiersPaged(page,pageSize));

    }

    @GetMapping("/activeCashiers")
    public ResponseEntity getActiveCashiers(){

        return ResponseEntity.ok(cashierService.getActiveCashiers());
    }

    @GetMapping("/activeCashiers/{page}/{pageSize}")
    public ResponseEntity getActiveCashiersPaged(@PathVariable("page")int page,@PathVariable("pageSize")int pageSize){

        return ResponseEntity.ok(cashierService.getActiveCashiersPaged(page,pageSize));
    }

    @PostMapping("/cashierStatus/{id}/{status}")
    public ResponseEntity setCashierStatus(@PathVariable("status")boolean status,@PathVariable("id")int id){
        return ResponseEntity.ok(cashierService.setCashierStatus(id,status));
    }

}
