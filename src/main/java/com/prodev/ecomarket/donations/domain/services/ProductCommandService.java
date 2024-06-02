package com.prodev.ecomarket.donations.domain.services;

import com.prodev.ecomarket.donations.domain.model.commands.CreateProductCommand;
import com.prodev.ecomarket.donations.domain.model.entities.Product;

import java.util.Optional;

public interface ProductCommandService {
    Optional<Product> handle(CreateProductCommand command);
}
