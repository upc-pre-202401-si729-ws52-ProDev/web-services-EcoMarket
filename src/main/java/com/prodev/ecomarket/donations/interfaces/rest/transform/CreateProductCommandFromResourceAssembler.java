package com.prodev.ecomarket.donations.interfaces.rest.transform;

import com.prodev.ecomarket.donations.domain.model.commands.CreateProductCommand;
import com.prodev.ecomarket.donations.interfaces.rest.resources.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {
    public static CreateProductCommand fromResource(CreateProductResource resource) {
        return new CreateProductCommand(
            resource.name(),
            resource.description(),
            resource.type(),
            resource.quantity(),
            resource.defect(),
            resource.urlImage(),
            resource.price()
        );
    }
}
