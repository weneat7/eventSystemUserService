package com.example.eventsystemuserservice.services;

import com.example.eventsystemuserservice.models.Authorities;
import com.example.eventsystemuserservice.models.User;
import com.example.eventsystemuserservice.repositories.AuthoritiesRepository;
import com.example.eventsystemuserservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import static java.util.Arrays.asList;

@Service
public class SelfUserService implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthoritiesRepository authoritiesRepository;

    @Autowired
    public SelfUserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                           AuthoritiesRepository authoritiesRepository){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authoritiesRepository = authoritiesRepository;
    }

    @Override
    public User signUp(String name, String username, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setUserName(username);
        user.setEmail(email);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        Authorities authorities= authoritiesRepository.findByName("User");
        user.setAuthorities(new ArrayList<>(asList(authorities)));
        return userRepository.save(user);
    }

    @Override
    public void logIn(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUserName(username);
        if(optionalUser.isEmpty()){
            throw new RuntimeException("User doesn't exist");
        }
        String hashedPassword = optionalUser.get().getHashedPassword();
        if(!bCryptPasswordEncoder.matches(password, hashedPassword)){
            throw new RuntimeException("Wrong Credentials");
        }
    }

    @Override
    public void logOut() {

    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUserName(username).orElseThrow(() -> new RuntimeException("User doesn't exist"));
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        Optional<User> optionalUser = userRepository.findByUserName(user.getUserName());
        if(optionalUser.isEmpty()){
            throw new RuntimeException("User doesn't exist");
        }
        user.setDeleted(true);
        userRepository.save(user);
    }
}
