package com.alexahdp.spring.dto;

import com.alexahdp.spring.validation.UserInfo;
import lombok.Value;

import javax.validation.constraints.Size;

@Value
@UserInfo
public class UserUpdateDto {
    @Size(min = 3, max = 64)
    String firstName;

    @Size(min = 3, max = 64)
    String lastName;
}
