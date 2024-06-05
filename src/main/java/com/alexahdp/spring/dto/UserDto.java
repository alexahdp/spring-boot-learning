package com.alexahdp.spring.dto;

public record UserDto(Long id, String username, String birthDate, String firstname, String lastname, String role,
                      CompanyReadDto company) {
}
