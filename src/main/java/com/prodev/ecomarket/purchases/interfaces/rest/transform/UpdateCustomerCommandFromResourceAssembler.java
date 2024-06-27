package com.prodev.ecomarket.purchases.interfaces.rest.transform;

import com.prodev.ecomarket.purchases.domain.model.commands.UpdateCustomerCommand;
import com.prodev.ecomarket.purchases.interfaces.rest.resources.UpdateCustomerResource;

public class UpdateCustomerCommandFromResourceAssembler {
    public static UpdateCustomerCommand toCommandFromResource(Long customerId, UpdateCustomerResource resource) {
        return new UpdateCustomerCommand(
                customerId,
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
