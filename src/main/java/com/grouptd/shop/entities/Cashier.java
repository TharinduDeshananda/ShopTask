package com.grouptd.shop.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Cashier extends CustomUser{

    private boolean status = true;
    @Temporal(value= TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date addedDate;
    @OneToOne
    private CustomUser addedBy;
    @Temporal(value=TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date modifiedDate;
    @OneToOne
    private CustomUser modifiedBy;

    public Cashier() {
        super();
    }

    public Cashier(String fullName, String userName, String password, boolean enabled,
                   List<String> authorities, boolean status, Date addedDate, Admin addedBy,
                   Date modifiedDate, Admin modifiedBy) {
        super(fullName, userName, password, enabled, authorities);
        this.status = status;
        this.addedDate = addedDate;
        this.addedBy = addedBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public CustomUser getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(CustomUser addedBy) {
        this.addedBy = addedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public CustomUser getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(CustomUser modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
