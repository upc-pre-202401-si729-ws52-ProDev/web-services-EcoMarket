package com.prodev.ecomarket.Review.Domain.Models.Queries;

public record getReviewByProductQuery(String product) {
    public getReviewByProductQuery {
        if (product == null || product.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }
}



