package com.dvachserver.testserver.Entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "OWNER_ID")
    private Collection<Location> locations;

    public OwnerContact() {
    }

    public OwnerContact(String ownerId, ArrayList<Contacts> contacts, ArrayList<Location> locations) {
        super();
        this.ownerId = ownerId;
        this.contacts = contacts;
        this.locations = locations;
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

    public Collection<Location> getLocations() {
        return locations;
    }

    public void setLocations(Collection<Location> locations) {
        this.locations = locations;
    }

    public void addLocation(Location location) {
        this.locations.add(location);
    }
}