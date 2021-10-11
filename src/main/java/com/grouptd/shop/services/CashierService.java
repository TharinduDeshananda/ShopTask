package com.grouptd.shop.services;

import com.grouptd.shop.dtos.CashierDto;
import com.grouptd.shop.entities.Cashier;
import com.grouptd.shop.entities.CustomUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public interface CashierService {
    public CashierDto createCashier(Cashier cashier);

    public CashierDto updateCasier(Cashier cashier);

    public boolean deleteCashier(@PathVariable("cashierId")int id);
    public List<CashierDto> getAllCashiers();

    public List<CashierDto> getPagedCashiers(int page,int pageSize);

    public List<CashierDto> getActiveCashiers();

    public List<CashierDto> getActiveCashiersPaged(@PathVariable("page")int page,@PathVariable("pageSize")int pageSize);

    public CashierDto setCashierStatus(int cashierId, boolean cashierStatus);
}
