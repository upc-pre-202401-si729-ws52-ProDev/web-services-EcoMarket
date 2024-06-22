package com.prodev.ecomarket.purchases.application.internal.queryservices;

import com.prodev.ecomarket.purchases.domain.model.entities.Customer;
import com.prodev.ecomarket.purchases.domain.model.queries.GetCustomerByNameQuery;
import com.prodev.ecomarket.purchases.domain.services.CustomerQueryService;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.CustomerRepository;

import java.util.Optional;

public class CustomerQueryServiceImpl implements CustomerQueryService {
    private final CustomerRepository customerRepository;

    public CustomerQueryServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> handle(GetCustomerByNameQuery query) {
        return customerRepository.findByName(query.CustomerName());
    }
}
