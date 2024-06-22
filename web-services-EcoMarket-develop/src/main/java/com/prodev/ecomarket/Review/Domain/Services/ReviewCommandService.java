package com.prodev.ecomarket.Review.Domain.Services;

import com.prodev.ecomarket.Review.Domain.Models.Aggregates.Review;
import com.prodev.ecomarket.Review.Domain.Models.Commands.CreaterReviewCommand;

import java.util.Optional;

public interface ReviewCommandService {
    Optional<Review> handle(CreaterReviewCommand command);

}
