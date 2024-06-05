package com.alexahdp.spring.database.repository;

import com.alexahdp.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query("SELECT c FROM Company c WHERE c.name = ?1")
    Optional<Company> findByName(String name);

    List<Company> findAllByNameContainingIgnoreCase(String fragment);

    // taken from PartTreeJpaQuery
//    Optional<Company> findById(Integer id);
//    void delete(Company entity);
}
