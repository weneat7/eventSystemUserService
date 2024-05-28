package com.example.eventsystemuserservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpRequestDto {
    private String name;
    private String username;
    private String email;
    private String password;
}
