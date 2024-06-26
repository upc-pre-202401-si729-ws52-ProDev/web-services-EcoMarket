package com.prodev.ecomarket.ordering.interfaces.rest.resources;

public record CreateCartItemResource(
    int quantity,
    Long productId,
    Long shoppingCartId
) {
}
