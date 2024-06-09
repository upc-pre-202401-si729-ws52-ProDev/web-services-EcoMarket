package com.prodev.ecomarket.Review.Infastructure.persistence.jpa.strategy;

import com.prodev.ecomarket.Review.Domain.Models.Aggregates.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewSourceRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByProduct(String product);
}
