package com.prodev.ecomarket.Review.Interface.rest.Transforms;

import com.prodev.ecomarket.Review.Domain.Models.Aggregates.Review;
import com.prodev.ecomarket.Review.Interface.rest.Resources.ReviewResources;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewResourceFromEntityAssembler{

    public static ReviewResources toResourceFromEntity(Review entity) {
        return new ReviewResources(
                entity.getId(),
                entity.getContent(),
                entity.getCalification(),
                entity.getDate(),
                entity.getProduct());
    }

    public static List<ReviewResources> toResourceFromEntityList(List<Review> entities) {
        return entities.stream()
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
    }
}