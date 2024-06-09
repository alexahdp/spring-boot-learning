package com.alexahdp.spring.integration.database.repository;

import com.alexahdp.spring.database.entity.Role;
import com.alexahdp.spring.database.repository.UserRepository;
import com.alexahdp.spring.integration.IntegrationTestBase;
import com.alexahdp.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
public class UserRepositoryTest extends IntegrationTestBase {
    private final UserRepository userRepository;


    @Test
    void checkBatchUser() {
        var users = userRepository.findAll();
        userRepository.updateCompanyAndRole(users);
    }

    @Test
    void checkJdbcTemplate() {
        var users = userRepository.findAllByCompanyIdAndRole(1, Role.USER);
        assertThat(users).hasSize(1);
    }

    @Test
    void checkQueries() {
        var users = userRepository.findAllBy("a", "ov");
        Assertions.assertThat(users).hasSize(3);
    }

    @Test
    void checkUpdate() {
        var resultCount = userRepository.updateRole(Role.USER, 1L, 2L);
        assertEquals(2, resultCount);
    }

    @Test
    void checkFirstTop() {
        var topUser = userRepository.findTopByOrderByIdDesc();
        assertTrue(topUser.isPresent());
        topUser.ifPresent(user -> assertEquals(5L, user.getId()));
    }

    @Test
    void checkSort() {
        var sortById = Sort.by("id").descending();
        var top3User = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sortById);
        assertThat(top3User).hasSize(3);
    }

    @Test
    void checkPageable() {
        var pageable = PageRequest.of(1, 2, Sort.by("id"));
        var users = userRepository.findAllBy(pageable);
        assertThat(users).hasSize(2);
    }
}
