package com.prodev.ecomarket.donations.infrastructure.persistence.jpa.repositories;

import com.prodev.ecomarket.donations.domain.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    List<Product> findByUserId(Long userId);
}
