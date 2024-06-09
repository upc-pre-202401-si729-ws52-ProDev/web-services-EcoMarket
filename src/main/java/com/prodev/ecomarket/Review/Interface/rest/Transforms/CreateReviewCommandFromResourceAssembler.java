package com.prodev.ecomarket.Review.Interface.rest.Transforms;

import com.prodev.ecomarket.Review.Domain.Models.Commands.CreaterReviewCommand;
import com.prodev.ecomarket.Review.Interface.rest.Resources.CreateReviewResources;

public class CreateReviewCommandFromResourceAssembler {
    public static CreaterReviewCommand toCommandFromResource(CreateReviewResources resource){

        return new CreaterReviewCommand(resource.content(), resource.calification(), resource.date(), resource.product());
    }
}
