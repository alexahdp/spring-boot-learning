package com.alexahdp.spring.database.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Slf4j
@Component("userRepository")
@RequiredArgsConstructor
public class UserRepository {
    @Value("${spring.datasource.url}")
    private final String dbUrl;

//    public Optional<User> findById(Integer id) {
//        return Optional.of(new User(
//                id,
//                "Alex",
//                LocalDate.of(1999, 12, 31),
//                "Alex",
//                "Ivanov",
//                Role.USER
//        ));
//    }

    public void findAll() {
        log.info(this.dbUrl);
    }
}
