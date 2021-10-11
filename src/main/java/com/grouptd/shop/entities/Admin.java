package com.grouptd.shop.entities;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Admin extends CustomUser{

    public Admin() {
        super();
    }

    public Admin(String fullName, String userName, String password, boolean enabled, List<String> authorities) {
        super(fullName, userName, password, enabled, authorities);
    }
}
