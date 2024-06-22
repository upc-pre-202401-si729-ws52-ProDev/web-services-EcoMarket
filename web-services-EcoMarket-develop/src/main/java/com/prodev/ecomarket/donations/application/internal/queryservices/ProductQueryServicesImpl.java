package com.prodev.ecomarket.donations.application.internal.queryservices;

import com.prodev.ecomarket.donations.domain.model.entities.Product;
import com.prodev.ecomarket.donations.domain.model.queries.GetProductByNameQuery;
import com.prodev.ecomarket.donations.domain.services.ProductQueryService;
import com.prodev.ecomarket.donations.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductQueryServicesImpl  implements ProductQueryService {
    private final ProductRepository productRepository;

    public ProductQueryServicesImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> handle(GetProductByNameQuery query) {
        return productRepository.findByName(query.name());
    }
}
