package com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories;
import com.prodev.ecomarket.purchases.domain.model.aggregates.Payments;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Date;


public interface PaymentRepository extends JpaRepository<Payments, Long> {
    List<Payments> findByCustomerId(Long customerId);
    List<Payments> findByCreatedAtBetween(Date startDate, Date endDate);

}
