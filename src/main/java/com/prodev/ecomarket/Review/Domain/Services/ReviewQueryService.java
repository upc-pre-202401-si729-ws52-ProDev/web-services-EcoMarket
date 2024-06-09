package com.prodev.ecomarket.Review.Domain.Services;
import com.prodev.ecomarket.Review.Domain.Models.Aggregates.Review;
import com.prodev.ecomarket.Review.Domain.Models.Queries.getReviewByProductQuery;
import java.util.Optional;

public interface ReviewQueryService {
    Optional<Review> handle(getReviewByProductQuery query);
}
