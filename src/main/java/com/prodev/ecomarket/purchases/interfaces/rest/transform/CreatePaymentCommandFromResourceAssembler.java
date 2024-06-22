package com.prodev.ecomarket.purchases.interfaces.rest.transform;

import com.prodev.ecomarket.purchases.domain.model.commands.CreatePaymentsCommand;
import com.prodev.ecomarket.purchases.interfaces.rest.resources.CreatePaymentResource;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.CustomerRepository;



public class CreatePaymentCommandFromResourceAssembler {

    public static CreatePaymentsCommand toCommandFromResource(CreatePaymentResource resource) {

        return new CreatePaymentsCommand(
                resource.amount(),
                resource.description(),
                resource.method(),
                resource.customerId()
        );
    }
}
