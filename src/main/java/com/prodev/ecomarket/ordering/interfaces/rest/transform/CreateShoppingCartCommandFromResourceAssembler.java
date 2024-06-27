package com.prodev.ecomarket.ordering.interfaces.rest.transform;

import com.prodev.ecomarket.ordering.domain.model.commands.CreateShoppingCartCommand;
import com.prodev.ecomarket.ordering.interfaces.rest.resources.CreateShoppingCartResource;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.CustomerRepository;

public class CreateShoppingCartCommandFromResourceAssembler {
    private final CustomerRepository customerRepository;

    public CreateShoppingCartCommandFromResourceAssembler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static CreateShoppingCartCommand fromResource(CreateShoppingCartResource resource, CustomerRepository customerRepository) {
        return new CreateShoppingCartCommand(
                customerRepository.findById(resource.customerId()).orElseThrow(
                        () -> new IllegalArgumentException("Customer not found"))
        );
    }
}
