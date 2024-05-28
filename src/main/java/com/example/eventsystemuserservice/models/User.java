package com.example.eventsystemuserservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String name;
    @ManyToMany
    private List<Authorities> authorities;
    private String userName;
    private String email;
    private String hashedPassword;
}
