package com.prodev.ecomarket.purchases.domain.services;

import com.prodev.ecomarket.purchases.domain.model.entities.Customer;
import com.prodev.ecomarket.purchases.domain.model.queries.GetCustomerByNameQuery;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CustomerQueryService {
    Optional<Customer> handle(GetCustomerByNameQuery query);
}
