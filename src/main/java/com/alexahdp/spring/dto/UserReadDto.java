package com.alexahdp.spring.dto;

import lombok.Value;

@Value
public class UserReadDto {
    Long id;
    String username;
    String birthDate;
    String firstname;
    String lastname;
    String role;
    CompanyReadDto company;
}
