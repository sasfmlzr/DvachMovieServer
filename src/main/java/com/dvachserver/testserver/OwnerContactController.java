package com.dvachserver.testserver;


import com.dvachserver.testserver.Entity.OwnerContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OwnerContactController {

    @Autowired
    private OwnerContactValueRepository repository;

    @GetMapping("/contacts/get")
    public List<OwnerContact> all() {
        return repository.findAll();
    }

    @GetMapping("/contacts/get/{id}")
    public OwnerContact one(@PathVariable String id) {

        OwnerContact contacts = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find " + id));

        return contacts;
    }

    @PostMapping("/contacts/new")
    public OwnerContact newContacts(@RequestBody OwnerContact newContacts) {
        return repository.save(newContacts);
    }

    @PutMapping("/contacts/put/{id}")
    public OwnerContact replaceEmployee(@RequestBody OwnerContact newContacts, @PathVariable String id) {

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