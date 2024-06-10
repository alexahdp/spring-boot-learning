package com.alexahdp.spring.mapper;

import com.alexahdp.spring.database.entity.Company;
import com.alexahdp.spring.database.entity.User;
import com.alexahdp.spring.database.repository.CompanyRepository;
import com.alexahdp.spring.dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateMapper implements Mapper<UserCreateDto, User> {
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User map(UserCreateDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }

    private void copy(UserCreateDto object, User user) {
        user.setFirstname(object.getFirstName());
        user.setLastname(object.getLastName());
        user.setUsername(object.getUsername());
//        user.setPassword(object.getPassword());
        user.setBirthDate(object.getBirthDate());
        user.setRole(object.getRole());
        user.setCompany(getCompany(object.getCompanyId()));

        Optional.ofNullable(object.getPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);
    }

    public Company getCompany(Integer companyId) {
        return Optional.ofNullable(companyId)
                .flatMap(companyRepository::findById)
                .orElse(null);
    }
}
