package com.example.eventsystemuserservice.services;

import com.example.eventsystemuserservice.models.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User signUp(String name, String username, String email, String password);
    void logIn(String username, String password);
    void logOut();
    User getUser(String username);
    User updateUser(User user);
    void deleteUser(User user);
}
