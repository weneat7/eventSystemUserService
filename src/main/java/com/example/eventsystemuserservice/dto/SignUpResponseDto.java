package com.example.eventsystemuserservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDto {
    private String message;
    private String userName;
    private String email;
}
