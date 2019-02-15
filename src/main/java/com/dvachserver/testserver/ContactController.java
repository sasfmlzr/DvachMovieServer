package com.dvachserver.testserver;


import com.dvachserver.testserver.Entity.Contacts;
import com.dvachserver.testserver.Entity.OwnerContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//not needed
@RestController
public class ContactController {

    @Autowired
    private ContactsValueRepository repository;

    @GetMapping("/cont/get")
    public List<Contacts> all() {
        return repository.findAll();
    }
}