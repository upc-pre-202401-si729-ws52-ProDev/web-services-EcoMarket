package com.prodev.ecomarket.purchases.interfaces.rest.transform;

import com.prodev.ecomarket.purchases.domain.model.commands.CreateCustommerCommand;
import com.prodev.ecomarket.purchases.interfaces.rest.resources.CreateCustomerResource;

public class CreateCustomerCommandFromResourceAssembles {
    public static CreateCustommerCommand toCommandFromResource(CreateCustomerResource resource) {
        return new CreateCustommerCommand(
                resource.loyaltyPoi(),
                resource.name(),
                resource.lastName(),
                resource.age(),
                resource.address(),
                resource.email(),
                resource.phone()
        );
    }
}
