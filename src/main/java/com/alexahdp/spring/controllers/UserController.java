package com.alexahdp.spring.controllers;

import com.alexahdp.spring.dto.UserCreateDto;
import com.alexahdp.spring.dto.UserDto;
import com.alexahdp.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "User not found"));
    }

    @PostMapping("/users")
    public UserDto createUser(
            @RequestBody UserCreateDto userCreateDto
            ) {
        return userService.create(userCreateDto);
    }
}
