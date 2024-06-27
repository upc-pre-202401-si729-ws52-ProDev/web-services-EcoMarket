package com.prodev.ecomarket.purchases.interfaces.rest.transform;

import com.prodev.ecomarket.purchases.domain.model.commands.CreatePurchaseCommand;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.PaymentRepository;
import com.prodev.ecomarket.purchases.interfaces.rest.resources.CreatePurchaseResource;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.CustomerRepository;

public class CreatePurchaseCommandFromResourceAssembler {
    public static CreatePurchaseCommand toCommandFromResource(CreatePurchaseResource resource, CustomerRepository customerRepository, PaymentRepository paymentRepository){
        return new CreatePurchaseCommand(
                resource.totalAmount(),
                resource.status(),
                resource.paymentMethod(),
                resource.customerId(),
                resource.paymentId());
    }
}
