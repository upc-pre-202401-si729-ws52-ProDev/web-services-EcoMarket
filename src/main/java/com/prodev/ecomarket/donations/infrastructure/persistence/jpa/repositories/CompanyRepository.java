package com.prodev.ecomarket.donations.infrastructure.persistence.jpa.repositories;

import com.prodev.ecomarket.donations.domain.model.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByName(String name);
    Optional<Company> findById(Long id);
}
