package com.alexahdp.spring.integration.service;

import com.alexahdp.spring.service.UserService;
import com.alexahdp.spring.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

// @SpringBootTest - for integration tests
@SpringBootTest
public class UserServiceIT {

    private static final Integer USER_ID = 1;

    @Autowired
    private UserService userService;

//    @Test
//    void sayHello() {
//        var actualResult = this.userService.sayHello(USER_ID);
//        Assertions.assertTrue(actualResult.isPresent());
//        var expectedResult = new UserDto(USER_ID);
//        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
//    }
}
