package com.alexahdp.spring.database.repository;

import com.alexahdp.spring.database.entity.User;
import com.alexahdp.spring.database.pool.ConnectionPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component("userRepository")
@RequiredArgsConstructor
public class UserRepository {
    @Value("${db.url}")
    private final String dbUrl;

//    public UserRepository(@Value("${db.url}") String dbUrl) {
//        this.dbUrl = dbUrl;
//    }

    public Optional<User> findById(Integer id) {
        return Optional.of(new User(id));
    }

    public void findAll() {
//        System.out.println(this.dbUrl);
        log.info(this.dbUrl);
    }
}
