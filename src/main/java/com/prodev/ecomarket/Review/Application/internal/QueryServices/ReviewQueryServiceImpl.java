package com.prodev.ecomarket.Review.Application.internal.QueryServices;

import com.prodev.ecomarket.Review.Domain.Models.Aggregates.Review;
import com.prodev.ecomarket.Review.Domain.Models.Queries.getReviewByProductQuery;
import com.prodev.ecomarket.Review.Domain.Services.ReviewQueryService;
import com.prodev.ecomarket.Review.Infastructure.persistence.jpa.strategy.ReviewSourceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewSourceRepository reviewSourceRepository;

    public ReviewQueryServiceImpl(ReviewSourceRepository reviewSourceRepository) {
        this.reviewSourceRepository = reviewSourceRepository;

    }

    @Override
    public Optional<Review> handle(getReviewByProductQuery query) {
        return reviewSourceRepository.findByProduct(query.product());    }
}

