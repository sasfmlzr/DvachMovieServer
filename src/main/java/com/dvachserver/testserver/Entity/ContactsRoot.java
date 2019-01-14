package com.dvachserver.testserver.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
public class ContactsRoot implements Serializable {

    @Id
    private String id;

    @Column(name="contacts")
    private ArrayList<Contacts> contacts;

    public ContactsRoot() {
    }

    public ContactsRoot(String id, ArrayList<Contacts> contacts) {
        super();
        this.id = id;
        this.contacts = contacts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }
}