package com.dvachserver.testserver;

import com.dvachserver.testserver.Entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationValueRepository extends
        JpaRepository<Location, String> {
}