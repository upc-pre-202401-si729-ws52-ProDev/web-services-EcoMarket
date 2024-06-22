package com.prodev.ecomarket.Review.Interface.rest;
import com.prodev.ecomarket.Review.Domain.Models.Aggregates.Review;
import com.prodev.ecomarket.Review.Domain.Models.Queries.getReviewByProductQuery;
import com.prodev.ecomarket.Review.Domain.Services.ReviewCommandService;
import com.prodev.ecomarket.Review.Domain.Services.ReviewQueryService;
import com.prodev.ecomarket.Review.Interface.rest.Resources.CreateReviewResources;
import com.prodev.ecomarket.Review.Interface.rest.Resources.ReviewResources;
import com.prodev.ecomarket.Review.Interface.rest.Transforms.CreateReviewCommandFromResourceAssembler;
import com.prodev.ecomarket.Review.Interface.rest.Transforms.ReviewResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    public ReviewController(ReviewCommandService reviewCommandService, ReviewQueryService reviewQueryService) {
        this.reviewCommandService = reviewCommandService;
        this.reviewQueryService = reviewQueryService;
    }

    @PostMapping("/create")
    public ResponseEntity<ReviewResources> createReview(@RequestBody CreateReviewResources resource) {
        Optional<Review> reviewSource = reviewCommandService.handle(CreateReviewCommandFromResourceAssembler.toCommandFromResource(resource));
        return reviewSource.map(source -> new ResponseEntity<>(ReviewResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/product/{product}")
    public ResponseEntity<ReviewResources> getReviewByProduct(@PathVariable String product) {
        Optional<Review> reviewSource = reviewQueryService.handle(new getReviewByProductQuery(product));
        return reviewSource.map(source -> ResponseEntity.ok(ReviewResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}