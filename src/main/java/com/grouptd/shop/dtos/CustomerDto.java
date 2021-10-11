package com.grouptd.shop.dtos;

import com.grouptd.shop.entities.CustomUser;
import com.grouptd.shop.entities.CustomerOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto extends CustomUserDto{

    private boolean status=true;
    private Date addedDate;
    private CustomUserDto addedBy;
    private Date modifiedDate;
    private CustomUserDto modifiedBy;
    private List<CustomOrderDto> customerOrders;
    private String address;



}
