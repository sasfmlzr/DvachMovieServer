package com.dvachserver.testserver;

import com.dvachserver.testserver.Entity.OwnerContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerContactValueRepository extends
        JpaRepository<OwnerContact, String> {
}