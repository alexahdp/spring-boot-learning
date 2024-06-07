package com.alexahdp.spring.controllers;

import com.alexahdp.spring.dto.UserCreateDto;
import com.alexahdp.spring.dto.UserDto;
import com.alexahdp.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
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
            @RequestBody @Validated UserCreateDto userCreateDto
//            BindingResult bindingResult
            ) {
//        if (bindingResult.hasErrors()) {
//            throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "Invalid user data");
//        }
        return userService.create(userCreateDto);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }

//    @PostMapping("/users")
//    public UserDto createUser(
//            @RequestBody @Validated UserCreateDto userCreateDto,
//            BindingResult bindingResult
//    ) {
//        if (bindingResult.hasErrors()) {
//            throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "Invalid user data");
//        }
//        return userService.create(userCreateDto);
//    }
}
