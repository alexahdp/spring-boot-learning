package com.alexahdp.spring.service;

import com.alexahdp.spring.database.repository.CompanyRepository;
import com.alexahdp.spring.dto.CompanyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Optional<CompanyDto> findById(Integer companyId) {
        return this.companyRepository.findById(companyId).map(entity -> {
            log.info(String.format("Hello, %d", entity.getId()));
            return new CompanyDto(entity.getId());
        });
    }
}
