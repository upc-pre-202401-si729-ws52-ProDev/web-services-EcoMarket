package com.prodev.ecomarket.iam.interfaces.rest.transform;

import com.prodev.ecomarket.iam.interfaces.rest.resources.SignUpResource;
import com.prodev.ecomarket.purchases.domain.model.commands.CreateCustommerCommand;

public class CreateCustomerCommandFromSignUpResource {
    public static CreateCustommerCommand toCreateCustomerCommand(SignUpResource resource) {
        return new CreateCustommerCommand(
                resource.loyaltyPoi().get(),
                resource.name().get(),
                resource.lastName().get(),
                resource.age().get(),
                resource.address().get(),
                resource.email().get(),
                resource.phone().get()
        );
    }
}
