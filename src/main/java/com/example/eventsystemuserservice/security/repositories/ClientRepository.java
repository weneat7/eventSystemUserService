package com.example.eventsystemuserservice.security.repositories;

import com.example.eventsystemuserservice.security.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);

    Client save(Client client);
}