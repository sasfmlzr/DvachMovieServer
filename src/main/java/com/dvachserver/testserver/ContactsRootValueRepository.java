package com.dvachserver.testserver;

import com.dvachserver.testserver.Entity.ContactsRoot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRootValueRepository extends
        JpaRepository<ContactsRoot, String> {
}