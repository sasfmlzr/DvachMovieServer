package com.dvachserver.testserver;

import com.dvachserver.testserver.Entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsValueRepository extends
        JpaRepository<Contacts, String> {
}