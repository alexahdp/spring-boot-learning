package com.alexahdp.spring.service;

import com.alexahdp.spring.database.entity.User;
import com.alexahdp.spring.database.repository.UserRepository;
import com.alexahdp.spring.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verifyNoMoreInteractions;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private static final Long USER_ID = 1L;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

//    @Test
//    void sayHello() {
//        Mockito.doReturn(Optional.of(new User(USER_ID)))
//                .when(userRepository).findById(USER_ID);
//        var actualResult = this.userService.sayHello(USER_ID);
//        Assertions.assertTrue(actualResult.isPresent());
//        var expectedResult = new UserDto(USER_ID);
//        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
//
//        verifyNoMoreInteractions(userRepository);
//    }
}
