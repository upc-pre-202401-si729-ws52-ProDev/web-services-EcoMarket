package com.prodev.ecomarket.purchases.interfaces.rest.transform;

import com.prodev.ecomarket.purchases.domain.model.commands.CreatePaymentsCommand;
import com.prodev.ecomarket.purchases.interfaces.rest.resources.CreatePaymentRequest;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.CustomerRepository;



public class CreatePaymentCommandFromResourceAssembler {

    public static CreatePaymentsCommand toCommandFromResource(CreatePaymentRequest resource, CustomerRepository customerRepository) {
        Long customerId = customerRepository.findById(resource.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found")).getId();

        return new CreatePaymentsCommand(
                resource.getAmount(),
                resource.getDescription(),
                resource.getMethod(),
                customerId
        );
    }
}
