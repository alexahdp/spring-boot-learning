package com.alexahdp.spring.integration.database.repository;

import com.alexahdp.spring.database.entity.Company;
import com.alexahdp.spring.database.repository.CompanyRepository;
import com.alexahdp.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
@Transactional
public class CompanyRepositoryTest {
    private final Integer APPLE_ID = 5;
    private final EntityManager entityManager;
    private final CompanyRepository companyRepository;

    @Test
    void checkFindByQueries() {
        companyRepository.findByName("Apple");
        companyRepository.findAllByNameContainingIgnoreCase("pp");
    }

    @Test
    public void findById() {
        var company = entityManager.find(Company.class, 1);
        assertNotNull(company);
        assertThat(company.getLocales()).hasSize(2);
    }

    @Test
    public void delete() {
        var maybeCompany = companyRepository.findById(APPLE_ID);
        assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(companyRepository::delete);
        entityManager.flush();
        assertTrue(companyRepository.findById(APPLE_ID).isEmpty());
    }

    @Test
    public void save() {
        var company = Company.builder()
                .name("Apple1")
                .locales(Map.of(
                        "en", "Apple",
                        "ru", "Яблоко"
                ))
                .build();
        entityManager.persist(company);
        assertNotNull(company.getId());
    }
}
