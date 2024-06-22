package com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories;

import com.prodev.ecomarket.purchases.domain.model.aggregates.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>{
    List<Purchase> findByCustomerId(Long customerId);
}
