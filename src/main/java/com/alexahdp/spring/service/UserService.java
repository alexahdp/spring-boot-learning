package com.alexahdp.spring.service;

import com.alexahdp.spring.database.repository.UserRepository;
import com.alexahdp.spring.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public Optional<UserDto> sayHello(Integer userId) {
        return Optional.of(new UserDto(userId));
//        return this.userRepository.findById(userId).map(entity -> {
//            log.info(String.format("Hello, %d", entity.id()));
//            return new UserDto(entity.id());
//        });
    }
}
