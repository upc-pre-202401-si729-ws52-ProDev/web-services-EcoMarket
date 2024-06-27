package com.prodev.ecomarket.ordering.interfaces.rest.resources;

public record CartItemResource(
        Long id,
        Long productId,
        int quantity,
        Long shoppingCartId
) {
}
