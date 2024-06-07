package com.alexahdp.spring.validation.impl;

import com.alexahdp.spring.dto.UserCreateDto;
import com.alexahdp.spring.validation.UserInfo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static org.springframework.util.StringUtils.hasText;

public class UserInfoValidator implements ConstraintValidator<UserInfo, UserCreateDto> {

    @Override
    public boolean isValid(UserCreateDto value, ConstraintValidatorContext context) {
        return hasText(value.getFirstName()) || hasText(value.getLastName());
    }
}
