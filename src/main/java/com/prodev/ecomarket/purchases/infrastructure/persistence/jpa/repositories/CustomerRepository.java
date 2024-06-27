package com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories;

import com.prodev.ecomarket.purchases.domain.model.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByName(String name);
}