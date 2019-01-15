package com.dvachserver.testserver.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class OwnerContact implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String ownerId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "OWNER_ID")
    private Collection<Contacts> contacts;

    public OwnerContact() {
    }

    public OwnerContact(String ownerId, ArrayList<Contacts> contacts) {
        super();
        this.ownerId = ownerId;
        this.contacts = contacts;
    }

    public String getId() {
        return ownerId;
    }

    public void setId(String id) {
        this.ownerId = id;
    }

    public Collection<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }
}