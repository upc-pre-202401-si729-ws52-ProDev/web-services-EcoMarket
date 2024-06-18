package com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories;
import com.prodev.ecomarket.purchases.domain.model.aggregates.Payments;

import com.prodev.ecomarket.purchases.domain.model.valueobjects.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;


@Repository
public interface PaymentRepository extends JpaRepository<Payments, Long> {
    List<Payments> findByCustomerId(Long customerId);
    List<Payments> findByStatus(PaymentStatus status);

}
