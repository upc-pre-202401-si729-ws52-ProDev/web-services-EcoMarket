package com.prodev.ecomarket.ordering.interfaces.rest.resources;

import java.util.List;

public record ShoppingCartResource(
        Long id,
        double total,
        List<CartItemResource> cartItems
) {
}
