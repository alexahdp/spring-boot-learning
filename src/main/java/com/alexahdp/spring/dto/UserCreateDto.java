package com.alexahdp.spring.dto;

import lombok.Value;

@Value
public class UserCreateDto {
    String firstName;
    String lastName;
    String username;
    String password;
    String birthDate;
    String role;
    Integer companyId;
}
