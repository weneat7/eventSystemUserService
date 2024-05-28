package com.example.eventsystemuserservice.controllers;

import com.example.eventsystemuserservice.dto.LogInRequestDto;
import com.example.eventsystemuserservice.dto.LogInResponseDto;
import com.example.eventsystemuserservice.dto.SignUpRequestDto;
import com.example.eventsystemuserservice.dto.SignUpResponseDto;
import com.example.eventsystemuserservice.models.User;
import com.example.eventsystemuserservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        String name = signUpRequestDto.getName();
        String email = signUpRequestDto.getEmail();
        String password = signUpRequestDto.getPassword();
        String username = signUpRequestDto.getUsername();

        User user = userService.signUp(name, email, password, username);
        return new ResponseEntity<>(convertToSignUpResponseDto(user), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<LogInResponseDto> signIn(@RequestBody LogInRequestDto logInRequestDto){
        return null;
    }

    @PostMapping("/signout")
    public ResponseEntity<String> signOut(){
        return null;
    }

    private SignUpResponseDto convertToSignUpResponseDto(User user){
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        signUpResponseDto.setMessage("User created successfully");
        signUpResponseDto.setEmail(user.getEmail());
        signUpResponseDto.setUserName(user.getUserName());
        return signUpResponseDto;
    }


}
