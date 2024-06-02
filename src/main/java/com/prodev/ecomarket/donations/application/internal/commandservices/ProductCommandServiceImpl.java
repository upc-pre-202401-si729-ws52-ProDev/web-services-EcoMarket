package com.prodev.ecomarket.donations.application.internal.commandservices;

import com.prodev.ecomarket.donations.domain.model.commands.CreateProductCommand;
import com.prodev.ecomarket.donations.domain.model.entities.Product;
import com.prodev.ecomarket.donations.domain.services.ProductCommandService;
import com.prodev.ecomarket.donations.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductRepository productRepository;

    public ProductCommandServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> handle(CreateProductCommand command) {
        var product = new Product(command);
        try {
            productRepository.save(product);
            return Optional.of(product);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving product", e);
        }
    }
}
