package com.dvachserver.testserver;


import com.dvachserver.testserver.Entity.ContactsRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactsRootController {

    @Autowired
    private ContactsRootValueRepository repository;

    @GetMapping("/contacts/get")
    public List<ContactsRoot> all() {
        List<ContactsRoot> contacts = repository.findAll();
        return contacts;
    }

    @GetMapping("/")
    public String fff() {
        String str = "GGGGG";
        return str;
    }

    @GetMapping("/contacts/get/{id}")
    public ContactsRoot one(@PathVariable String id) {

        ContactsRoot contacts = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find " + id));

        return contacts;
    }

    @PostMapping("/contacts/new")
    public ContactsRoot newContacts(@RequestBody ContactsRoot newContacts) {
        return repository.save(newContacts);
    }

    @PutMapping("/contacts/put/{id}")
    public ContactsRoot replaceEmployee(@RequestBody ContactsRoot newContacts, @PathVariable String id) {

        return repository.findById(id)
                .map(contacts -> {
                    contacts.setContacts(newContacts.getContacts());
                    return repository.save(contacts);
                })
                .orElseGet(() -> {
                    newContacts.setId(id);
                    return repository.save(newContacts);
                });
    }
}