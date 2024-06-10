package com.alexahdp.spring.service;

import com.alexahdp.spring.database.repository.UserRepository;
import com.alexahdp.spring.database.entity.User;
import com.alexahdp.spring.dto.*;
import com.alexahdp.spring.mapper.UserCreateMapper;
import com.alexahdp.spring.mapper.UserReadMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateMapper userCreateMapper;
    private final ImageService imageService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
            .map(user -> new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(user.getRole())
            ))
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }

    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id).map(userReadMapper::map);
    }

    public final UserDto create(UserCreateDto userCreateDto) {
        return Optional.of(userCreateDto).map(userCreateMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    public final UserDto update(Long userId, UserUpdateDto userUpdateDto) {
        return userRepository.findById(userId).map(u -> {
            u.setFirstname(userUpdateDto.getFirstName());
            u.setLastname(userUpdateDto.getLastName());
            userRepository.save(u);
            return u;
        }).map(userReadMapper::map).orElseThrow();
    }

    public final UserDto setImage(Long userId, MultipartFile file, String name) {
        return userRepository.findById(userId).map(u -> {
            uploadImage(file);
            u.setImage(name);
            userRepository.save(u);
            return u;
        }).map(userReadMapper::map).orElseThrow();
    }

    @SneakyThrows
    private void uploadImage(MultipartFile image) {
        if (image.isEmpty()) {
            throw new IllegalArgumentException("Image is empty");
        }
        imageService.uploadImage(image.getOriginalFilename(), image.getInputStream());
    }

    public final boolean deleteById(Long id) {
        userRepository.deleteById(id);
        return true;
    }

    public final Optional<byte[]> findAvatar(Long userId) {
    return userRepository.findById(userId)
                .map(User::getImage)
                .filter(StringUtils::hasText)
                .flatMap(imageService::get);
    }
}