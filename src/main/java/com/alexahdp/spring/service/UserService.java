package com.alexahdp.spring.service;

import com.alexahdp.spring.database.repository.UserRepository;
//import com.alexahdp.spring.dto.UserCreateDto;
import com.alexahdp.spring.dto.UserDto;
//import com.alexahdp.spring.dto.UserReadDto;
//import com.alexahdp.spring.mapper.UserCreateMapper;
import com.alexahdp.spring.mapper.UserReadMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
//    private final UserCreateMapper userCreateMapper;


    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }

    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id).map(userReadMapper::map);
    }

//    public final UserReadDto create(UserCreateDto userCreateDto) {
//        return Optional.of(userCreateDto).map(userCreateMapper::map)
//                .map(userRepository::save)
//                .map(userReadMapper::map)
//                .orElseThrow();
//    }
}
