package com.grouptd.shop.services.impl;

import com.grouptd.shop.dtos.CashierDto;
import com.grouptd.shop.entities.Cashier;
import com.grouptd.shop.entities.CustomUser;
import com.grouptd.shop.repositories.CashierDao;
import com.grouptd.shop.services.CashierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CashierServiceImpl implements CashierService {
    @Autowired
    CashierServiceImpl cashierService;

    @Autowired
    CashierDao cashierDao;
    @Autowired
    ModelMapper modelMapper;

    public CashierDto createCashier(Cashier cashier){
        CustomUser creator = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cashier.setAddedBy(creator);
        cashier.setModifiedBy(creator);
        cashier.getUserAuthorities().add("ROLE_CASHIER");
        Cashier cashier1 = cashierDao.save(cashier);


        return modelMapper.map(cashier1,CashierDto.class);
    }

    public CashierDto updateCasier(Cashier cashier){
        CustomUser creator = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cashier.setModifiedBy(creator);
        Cashier cashier1 = cashierDao.save(cashier);
        return modelMapper.map(cashier1,CashierDto.class);
    }

    public boolean deleteCashier(@PathVariable("cashierId")int id){
        cashierDao.deleteById(id);
        return true;
    }

    public List<CashierDto> getAllCashiers(){

        Iterable<Cashier> iterableCashier = cashierDao.findAll();
        Iterator<Cashier> cashierIterator = iterableCashier.iterator();
        List<CashierDto> dtos = new ArrayList<>();
        while(cashierIterator.hasNext()){
            dtos.add(modelMapper.map(cashierIterator.next(),CashierDto.class));
        }

        return dtos;
    }

    public List<CashierDto> getPagedCashiers(int page,int pageSize){
        PageRequest request = PageRequest.of(page,pageSize);
        Page<Cashier> cashierPage = cashierDao.findAll(request);

        return cashierPage.getContent().stream().map(cashier->{return modelMapper.map(cashier,CashierDto.class);}).collect(Collectors.toList());
    }

    public List<CashierDto> getActiveCashiers(){
        List<Cashier> activeCashiers = cashierDao.findActiveCashiers();

        return activeCashiers.stream().map(cashier->{return modelMapper.map(cashier,CashierDto.class);}).collect(Collectors.toList());
    }

    public List<CashierDto> getActiveCashiersPaged(@PathVariable("page")int page,@PathVariable("pageSize")int pageSize){
        Page<Cashier> activeCashiers = cashierDao.findActiveCashiers(PageRequest.of(page,pageSize));
        return activeCashiers.getContent().stream().map(cashier->{return modelMapper.map(cashier,CashierDto.class);}).collect(Collectors.toList());
    }

    public CashierDto setCashierStatus(int cashierId, boolean cashierStatus){
        Cashier cashier = cashierDao.findById(cashierId).get();
        cashier.setStatus(cashierStatus);
        cashier.setEnabled(cashierStatus);
        cashier = cashierDao.save(cashier);
        return modelMapper.map(cashier,CashierDto.class);
    }


}
