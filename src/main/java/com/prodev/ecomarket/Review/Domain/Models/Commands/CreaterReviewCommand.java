package com.prodev.ecomarket.Review.Domain.Models.Commands;

import java.time.LocalDate;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

public record CreaterReviewCommand(String content, int calification, LocalDate date, String product) {

    public CreaterReviewCommand {

        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("content cannot be null or empty");
        }
        if (calification < 0 || calification > 5) {
            throw new IllegalArgumentException("calification must be between 0 and 5");
        }
        if (date == null) {
            throw new IllegalArgumentException("date cannot be null");
        }
        if (product == null || product.isBlank()) {
            throw new IllegalArgumentException("product cannot be null or empty");
        }
    }
}