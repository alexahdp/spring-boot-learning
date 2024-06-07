package com.alexahdp.spring.dto;

import com.alexahdp.spring.database.entity.Role;
import com.alexahdp.spring.validation.UserInfo;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
@UserInfo
public class UserCreateDto {

    @Size(min = 3, max = 64)
    String firstName;

    @Size(min = 3, max = 64)
    String lastName;

    String username;

    String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;

    Role role;

    Integer companyId;
}
