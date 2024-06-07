package com.alexahdp.spring.controllers;

import com.alexahdp.spring.dto.UserCreateDto;
import com.alexahdp.spring.dto.UserDto;
import com.alexahdp.spring.dto.UserSetImageDto;
import com.alexahdp.spring.dto.UserUpdateDto;
import com.alexahdp.spring.service.UserService;
import io.swagger.v3.oas.annotations.headers.Header;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.findAll();
    }

    // produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    @GetMapping(value = "/users/{id}/avatar", produces = { "image/png" })
    public ResponseEntity<byte[]> getUserAvatar(
            @PathVariable("id") Long userId
    ) {
        return userService.findAvatar(userId)
                .map(content -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE)
                        .contentLength(content.length)
                        .body(content))
                .orElseGet(ResponseEntity.notFound()::build);
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

    @PostMapping(value = "/users/{id}/image", consumes = { "multipart/form-data" })
    public UserDto setUserImage(
            @PathVariable("id") Long userId,
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file
    ) {
        return userService.setImage(userId, file, name);
    }

    @PutMapping("/users/{id}")
    public UserDto setUserImage(
            @PathVariable("id") Long userId,
            @RequestBody @Validated UserUpdateDto userUpdateDto
    ) {
        return userService.update(userId, userUpdateDto);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        return userService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
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
