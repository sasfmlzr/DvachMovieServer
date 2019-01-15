package com.dvachserver.testserver;


import com.dvachserver.testserver.Entity.Contacts;
import com.dvachserver.testserver.Entity.OwnerContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ContactController {

    @Autowired
    private ContactsValueRepository repository;

    @GetMapping("/cont/get")
    public List<Contacts> all() {
        return repository.findAll();
    }

    @GetMapping("/cont/get/{id}")
    public Contacts one(@PathVariable String id) {

        Contacts contacts = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find " + id));

        return contacts;
    }

    @PostMapping("/cont/new")
    public Contacts newContacts(@RequestBody Contacts newContacts) {
        return repository.save(newContacts);
    }

    @PutMapping("/cont/put/{id}")
    public Contacts replaceEmployee(@RequestBody Contacts newContacts, @PathVariable String id) {

        return repository.findById(id)
                .map(contacts -> {
                    contacts.setName(newContacts.getName());
                    contacts.setPhone(newContacts.getPhone());
                    contacts.setOwnerContact(newContacts.getOwnerContact());
                    return repository.save(contacts);
                })
                .orElseGet(() -> {
                    return repository.save(newContacts);
                });
    }
}