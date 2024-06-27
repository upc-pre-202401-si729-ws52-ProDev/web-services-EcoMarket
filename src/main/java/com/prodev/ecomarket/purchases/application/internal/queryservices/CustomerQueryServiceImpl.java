package com.prodev.ecomarket.purchases.application.internal.queryservices;

import com.prodev.ecomarket.purchases.domain.model.entities.Customer;
import com.prodev.ecomarket.purchases.domain.model.queries.GetCustomerByIdQuery;
import com.prodev.ecomarket.purchases.domain.model.queries.GetCustomerByNameQuery;
import com.prodev.ecomarket.purchases.domain.services.CustomerQueryService;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerQueryServiceImpl implements CustomerQueryService {
    private final CustomerRepository customerRepository;

    public CustomerQueryServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> handle(GetCustomerByIdQuery query) {
        return customerRepository.findById(query.customerId());
    }
}
