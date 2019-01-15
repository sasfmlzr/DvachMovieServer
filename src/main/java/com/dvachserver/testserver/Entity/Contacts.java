package com.dvachserver.testserver.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Contacts implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="phone")
    private String phone;

    @ManyToOne
    private OwnerContact ownerContact;

    public Contacts() {
    }

    public Contacts(String name, String phone) {
        super();
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public OwnerContact getOwnerContact() {
        return ownerContact;
    }

    public void setOwnerContact(OwnerContact ownerContact) {
        this.ownerContact = ownerContact;
    }
}
