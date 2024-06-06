package com.alexahdp.spring.dto;

import com.alexahdp.spring.database.entity.Role;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserCreateDto {
    String firstName;
    String lastName;
    String username;
    String password;
    LocalDate birthDate;
    Role role;
    Integer companyId;
}
