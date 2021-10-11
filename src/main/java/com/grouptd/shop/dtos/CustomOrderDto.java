package com.grouptd.shop.dtos;

import com.grouptd.shop.entities.Customer;
import com.grouptd.shop.entities.CustomerOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomOrderDto {
    private int id;
    private double price;
    private int itemCount;
    private String description;

}
