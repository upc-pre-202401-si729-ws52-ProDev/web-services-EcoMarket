package com.prodev.ecomarket.donations.application.internal.commandservices;

import com.prodev.ecomarket.donations.domain.model.commands.CreateProductCommand;
import com.prodev.ecomarket.donations.domain.model.entities.Product;
import com.prodev.ecomarket.donations.domain.services.ProductCommandService;
import com.prodev.ecomarket.donations.infrastructure.persistence.jpa.repositories.ProductRepository;
import com.prodev.ecomarket.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.prodev.ecomarket.iam.interfaces.rest.resources.UserResource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ProductCommandServiceImpl(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> handle(CreateProductCommand command) {
        var user = userRepository.findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        var product = new Product(command, user);
        try {
            productRepository.save(product);
            return Optional.of(product);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving product", e);
        }
    }
}
