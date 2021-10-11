package com.grouptd.shop.dtos;

import com.grouptd.shop.entities.CustomUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CashierDto extends CustomUserDto{
    private boolean status = true;
    private Date addedDate;
    private CustomUserDto addedBy;
    private Date modifiedDate;
    private CustomUserDto modifiedBy;

}
