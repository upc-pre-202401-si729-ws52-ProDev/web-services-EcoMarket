package com.prodev.ecomarket.donations.interfaces.rest;


import com.prodev.ecomarket.donations.domain.services.ProductCommandService;
import com.prodev.ecomarket.donations.domain.services.ProductQueryService;
import com.prodev.ecomarket.donations.interfaces.rest.resources.CreateProductResource;
import com.prodev.ecomarket.donations.interfaces.rest.resources.DonationResource;
import com.prodev.ecomarket.donations.interfaces.rest.resources.ProductResource;
import com.prodev.ecomarket.donations.interfaces.rest.transform.CreateProductCommandFromResourceAssembler;
import com.prodev.ecomarket.donations.interfaces.rest.transform.ProductResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value ="/api/v1/products", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Products", description = "Products Management Endpoints")
public class ProductController {
    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    public ProductController(ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }

    @PostMapping
    public ResponseEntity<ProductResource> createProduct(@RequestBody CreateProductResource createProductResource) {
        var createProductCommand = CreateProductCommandFromResourceAssembler.fromResource(createProductResource);

        var product = productCommandService.handle(createProductCommand);

        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return new ResponseEntity<>(productResource, HttpStatus.CREATED);
    }

}
