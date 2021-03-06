package com.dvachserver.testserver;

import com.dvachserver.testserver.Entity.Contacts;
import com.dvachserver.testserver.Entity.Location;
import com.dvachserver.testserver.Entity.OwnerContact;
import com.dvachserver.testserver.Entity.StringResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    private OwnerContact setOwnerForContacts(OwnerContact oldContacts, OwnerContact newContacts) {
        for (Contacts cont : newContacts.getContacts()) {
            cont.setOwnerContact(newContacts);
        }
        oldContacts.setContacts((ArrayList<Contacts>) newContacts.getContacts());
        HashSet<Location> locations = new HashSet<>();

        Collection<Location> oldContactsLocation = oldContacts.getLocations();
        if (oldContactsLocation != null) {
            locations.addAll(oldContactsLocation);
        }

        Collection<Location> newContactsLocation = newContacts.getLocations();
        if (newContactsLocation != null) {
            locations.addAll(newContactsLocation);
        }
        oldContacts.setLocations(locations);
        return oldContacts;
    }

    @GetMapping("/contacts/getlocation/{id}")
    private Collection<Location> getLocationById(@PathVariable String id) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException("Could not find " + id)).getLocations();
    }

    @PutMapping("/contacts/putlocation/{id}")
    private OwnerContact addLocation(@RequestBody Location location, @PathVariable String id) {
        return repository.findById(id)
                .map(contacts -> {
                    contacts.addLocation(location);

                    return repository.save(contacts);
                })
                .orElseThrow(() ->
                        new RuntimeException("Could not find " + id));
    }

    @GetMapping(value = "/contacts/checkid/{id}")
    private StringResponse checkIdContactOwner(@PathVariable String id) {
        OwnerContact ownerContact = null;
        Optional<OwnerContact> optional = repository.findById(id);
        if (optional.isPresent()) {
            ownerContact = optional.get();
        }
        if (ownerContact == null) {
            return new StringResponse("It's unique id");
        } else {
            throw new RuntimeException("This login is already in use");
        }
    }
}
