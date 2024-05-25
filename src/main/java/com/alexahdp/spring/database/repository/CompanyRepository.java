package com.alexahdp.spring.database.repository;

import com.alexahdp.spring.database.entity.Company;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Component("companyRepository")
@RequiredArgsConstructor
public class CompanyRepository {

//    @Override
    public Optional<Company> findById(Integer id) {
        return Optional.of(new Company(id, null, Collections.emptyMap()));
    }

    public void delete(Company entity) {
        log.info("Delete company");
    }
}
