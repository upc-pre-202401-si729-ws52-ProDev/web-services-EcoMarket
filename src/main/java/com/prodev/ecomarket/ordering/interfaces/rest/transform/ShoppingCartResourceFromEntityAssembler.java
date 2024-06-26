package com.prodev.ecomarket.ordering.interfaces.rest.transform;

import com.prodev.ecomarket.ordering.domain.model.aggregates.ShoppingCart;
import com.prodev.ecomarket.ordering.interfaces.rest.resources.ShoppingCartResource;

public class ShoppingCartResourceFromEntityAssembler {
    public static ShoppingCartResource fromEntity(ShoppingCart entity) {
        return new ShoppingCartResource(
                entity.getId(),
                entity.getTotal(),
                entity.getCartItems().stream()
                        .map(CartItemResourceFromEntityAssembler::fromEntity).toList()
        );
    }
}
