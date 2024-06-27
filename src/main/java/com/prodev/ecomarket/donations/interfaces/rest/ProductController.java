package com.prodev.ecomarket.donations.interfaces.rest;


import com.prodev.ecomarket.donations.domain.services.ProductCommandService;
import com.prodev.ecomarket.donations.domain.services.ProductQueryService;
import com.prodev.ecomarket.donations.infrastructure.persistence.jpa.repositories.ProductRepository;
import com.prodev.ecomarket.donations.interfaces.rest.resources.CreateProductResource;
import com.prodev.ecomarket.donations.interfaces.rest.resources.DonationResource;
import com.prodev.ecomarket.donations.interfaces.rest.resources.ProductResource;
import com.prodev.ecomarket.donations.interfaces.rest.transform.CreateProductCommandFromResourceAssembler;
import com.prodev.ecomarket.donations.interfaces.rest.transform.ProductResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value ="/api/v1/products", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Products", description = "Products Management Endpoints")
public class ProductController {
    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;
    private final ProductRepository productRepository;

    public ProductController(ProductCommandService productCommandService, ProductQueryService productQueryService, ProductRepository productRepository) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<ProductResource> createProduct(@RequestBody CreateProductResource createProductResource) {
        var createProductCommand = CreateProductCommandFromResourceAssembler.fromResource(createProductResource);

        var product = productCommandService.handle(createProductCommand);

        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return new ResponseEntity<>(productResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResource>> getAllProducts() {
        var products = productRepository.findAll();
        List<ProductResource> productResources = products.stream()
                .map(ProductResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(productResources);
    }

    // En ProductController.java
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductResource>> getProductsByUserId(@PathVariable Long userId) {
        var products = productRepository.findByUserId(userId);
        List<ProductResource> productResources = products.stream()
                .map(ProductResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(productResources);
    }

}
