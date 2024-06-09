package com.prodev.ecomarket.donations.domain.services;

import com.prodev.ecomarket.donations.domain.model.entities.Product;
import com.prodev.ecomarket.donations.domain.model.queries.GetProductByNameQuery;

import java.util.Optional;

public interface ProductQueryService {
    Optional<Product> handle(GetProductByNameQuery query);
}
