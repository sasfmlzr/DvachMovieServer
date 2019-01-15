package com.dvachserver.testserver;


import com.dvachserver.testserver.Entity.Contacts;
import com.dvachserver.testserver.Entity.OwnerContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find " + id));
    }

    @PostMapping("/contacts/new")
    public OwnerContact newContacts(@RequestBody OwnerContact newContacts) {
        return repository.save(newContacts);
    }

    @PutMapping("/contacts/put/{id}")
    public OwnerContact replaceEmployee(@RequestBody OwnerContact newContacts, @PathVariable String id) {

        return repository.findById(id)
                .map(contacts -> repository.save(setOwnerForContacts(contacts, newContacts)))
                .orElseGet(() -> {
                    //wtf
                    newContacts.setId(id);
                    return repository.save(newContacts);
                });
    }

    private OwnerContact setOwnerForContacts(OwnerContact oldContacts, OwnerContact newContacts){
        for (Contacts cont: newContacts.getContacts()) {
            cont.setOwnerContact(newContacts);
        }
        oldContacts.setContacts((ArrayList<Contacts>) newContacts.getContacts());
        return oldContacts;
    }
}