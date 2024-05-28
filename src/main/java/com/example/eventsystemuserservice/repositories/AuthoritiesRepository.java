package com.example.eventsystemuserservice.repositories;

import com.example.eventsystemuserservice.models.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {
    Authorities findByName(String name);
}