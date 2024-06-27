package com.prodev.ecomarket.donations.interfaces.rest.transform;

import com.prodev.ecomarket.donations.domain.model.entities.Product;
import com.prodev.ecomarket.donations.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {
    public static ProductResource toResourceFromEntity(Product entity) {
        return new ProductResource(entity.getId(), entity.getName(), entity.getDescription(), entity.getType(),
                entity.getQuantity(), entity.getDefect(), entity.getUrlImage(), entity.getPrice());
    }
}
