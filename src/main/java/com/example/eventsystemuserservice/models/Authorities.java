package com.example.eventsystemuserservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Authorities extends BaseModel{
    private String name;

    public Authorities(String user) {
        super();
    }

    public Authorities() {

    }
}
