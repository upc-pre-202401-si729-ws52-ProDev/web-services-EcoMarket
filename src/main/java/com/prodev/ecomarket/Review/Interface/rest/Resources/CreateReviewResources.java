package com.prodev.ecomarket.Review.Interface.rest.Resources;

import java.time.LocalDate;

public record CreateReviewResources(Long id, String content, int calification, LocalDate date, String product) {

    public CreateReviewResources {

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