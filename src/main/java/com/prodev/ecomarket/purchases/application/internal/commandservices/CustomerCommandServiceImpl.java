package com.prodev.ecomarket.purchases.application.internal.commandservices;

import com.prodev.ecomarket.purchases.domain.model.commands.CreateCustommerCommand;
import com.prodev.ecomarket.purchases.domain.model.commands.UpdateCustomerCommand;
import com.prodev.ecomarket.purchases.domain.model.entities.Customer;
import com.prodev.ecomarket.purchases.domain.services.CustomerCommandService;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerCommandServiceImpl implements CustomerCommandService {
    private final CustomerRepository customerRepository;

    public CustomerCommandServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> handle(CreateCustommerCommand command){
        var customer = new Customer(command);
        var createdCustomer = customerRepository.save(customer);
        return Optional.of(createdCustomer);
    }

    @Override
    public Optional<Customer> handle(UpdateCustomerCommand command){
        var customer = customerRepository.findById(command.customerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        customer.setLoyaltyPoi(command.loyaltyPoi());
        customer.setName(command.name());
        customer.setLastName(command.lastName());
        customer.setAge(command.age());
        customer.setAddress(command.address());
        customer.setEmail(command.email());
        customer.setPhone(command.phone());
        customerRepository.save(customer);

        return Optional.of(customer);
    }
}
