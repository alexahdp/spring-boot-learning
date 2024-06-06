package com.alexahdp.spring.mapper;

import com.alexahdp.spring.database.entity.Company;
import com.alexahdp.spring.database.entity.User;
import com.alexahdp.spring.database.repository.CompanyRepository;
import com.alexahdp.spring.dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateMapper implements Mapper<UserCreateDto, User> {
    private final CompanyRepository companyRepository;

    @Override
    public User map(UserCreateDto object) {
        User user = new User();
        user.setFirstname(object.getFirstName());
        user.setLastname(object.getLastName());
        user.setUsername(object.getUsername());
//        user.setPassword(object.getPassword());
        user.setBirthDate(object.getBirthDate());
        user.setRole(object.getRole());
        user.setCompany(getCompany(object.getCompanyId()));
        return user;
    }

    public Company getCompany(Integer companyId) {
        return Optional.ofNullable(companyId).flatMap(
                companyRepository::findById
        ).orElse(null);
    }
}
