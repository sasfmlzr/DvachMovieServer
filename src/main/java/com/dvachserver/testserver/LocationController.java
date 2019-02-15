package com.dvachserver.testserver;

import com.dvachserver.testserver.Entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//not needed
public class LocationController {

    @Autowired
    private LocationValueRepository repository;

    @GetMapping("/contacts/location/get")
    public List<Location> all() {
        return repository.findAll();
    }

    @GetMapping("/contacts/location/get/{id}")
    public Location getOne(@PathVariable String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find " + id));
    }
}
