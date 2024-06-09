package com.prodev.ecomarket.Review.Application.internal.CommandServices;
import com.prodev.ecomarket.Review.Domain.Models.Aggregates.Review;
import com.prodev.ecomarket.Review.Domain.Models.Commands.CreaterReviewCommand;
import com.prodev.ecomarket.Review.Domain.Services.ReviewCommandService;
import com.prodev.ecomarket.Review.Infastructure.persistence.jpa.strategy.ReviewSourceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewSourceRepository reviewSourceRepository;

    public ReviewCommandServiceImpl(ReviewSourceRepository reviewSourceRepository) {
        this.reviewSourceRepository = reviewSourceRepository;
    }

    @Override
    public Optional<Review> handle(CreaterReviewCommand command) {

        var review = new Review(command);
        var createdReview = reviewSourceRepository.save(review);
        return Optional.of(createdReview);
    }
}
