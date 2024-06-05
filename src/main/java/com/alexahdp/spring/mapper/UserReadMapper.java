package com.alexahdp.spring.mapper;

import com.alexahdp.spring.database.entity.User;
import com.alexahdp.spring.dto.CompanyReadDto;
import com.alexahdp.spring.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserDto>{

    private final CompanyReadMapper companyReadMapper;

    @Override
    public UserDto map(User object) {
        CompanyReadDto company = Optional.ofNullable(object.getCompany()).map(companyReadMapper::map).orElse(null);
        return new UserDto(
                object.getId(),
                object.getUsername(),
                object.getBirthDate().toString(),
                object.getFirstname(),
                object.getLastname(),
                object.getRole().toString(),
                company
        );
    }
}
