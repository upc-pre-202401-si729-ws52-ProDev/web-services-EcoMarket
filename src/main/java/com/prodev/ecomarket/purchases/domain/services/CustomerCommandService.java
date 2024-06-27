package com.prodev.ecomarket.purchases.domain.services;

import com.prodev.ecomarket.purchases.domain.model.commands.CreateCustommerCommand;
import com.prodev.ecomarket.purchases.domain.model.commands.UpdateCustomerCommand;
import com.prodev.ecomarket.purchases.domain.model.entities.Customer;

import java.util.Optional;

public interface CustomerCommandService {
    Optional<Customer> handle(CreateCustommerCommand command);
    Optional<Customer> handle(UpdateCustomerCommand command);
}
