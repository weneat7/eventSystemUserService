package com.example.eventsystemuserservice.security.services;

import com.example.eventsystemuserservice.models.User;
import com.example.eventsystemuserservice.repositories.UserRepository;
import com.example.eventsystemuserservice.security.models.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserName(username);

        if(userOptional.isEmpty())
            throw new UsernameNotFoundException("UserName doesn't exists");

        User user = userOptional.get();
        return new CustomUserDetails(user);
    }
}
