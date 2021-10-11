package com.grouptd.shop;

import com.grouptd.shop.dtos.AdminDto;
import com.grouptd.shop.entities.Admin;
import com.grouptd.shop.repositories.AdminDao;
import com.grouptd.shop.services.AdminService;
import com.grouptd.shop.services.impl.AdminServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class ShopApplication implements CommandLineRunner {

	@Autowired
	AdminService adminServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}
	@Bean
	ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		Admin admin1 = new Admin("admin_full_name_1",
				"foo","foo",true,new ArrayList<>());
		admin1.getUserAuthorities().add("ROLE_ADMIN");

		AdminDto admin = adminServiceImpl.createAdmin(admin1);

	}
}
