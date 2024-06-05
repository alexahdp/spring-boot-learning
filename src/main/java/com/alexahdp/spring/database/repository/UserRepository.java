package com.alexahdp.spring.database.repository;

import com.alexahdp.spring.database.entity.Role;
import com.alexahdp.spring.database.entity.User;
import com.alexahdp.spring.dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Slf4j
@Component("userRepository")
@RequiredArgsConstructor
public class UserRepository {
    @Value("${spring.datasource.url}")
    private final String dbUrl;

//    public Optional<User> findById(Long id) {
//        return Optional.of(new User(
//                id,
//                "username",
//                LocalDate.of(1990, 1, 1),
//                "John",
//                "Doe",
//                Role.ADMIN
//        ));
//    }

    public List<User> findAll() {
//        var u = new User.builder()
//                .id(1L)
//                .username("username")
//                .birthDate(LocalDate.of(1990, 1, 1))
//                .firstname("John")
//                .lastname("Doe")
//                .role(Role.ADMIN)
//                .build();
        var u = new User(
                1L,
                "username",
                LocalDate.of(1990, 1, 1),
                "John",
                "Doe",
                Role.ADMIN,
                null,
                null
        );
        return List.of(u);
    }

    public Optional<User> findById(Long id) {
        var u = new User(
                id,
                "username",
                LocalDate.of(1990, 1, 1),
                "John",
                "Doe",
                Role.ADMIN,
                null,
                null
        );
        return Optional.of(u);
    }

    public User create(
            String username,
            LocalDate birthDate,

    ) {
        var u = new User(
                id,
                "username",
                LocalDate.of(1990, 1, 1),
                "John",
                "Doe",
                Role.ADMIN,
                null,
                null
        );
        return Optional.of(u);
    }
}
