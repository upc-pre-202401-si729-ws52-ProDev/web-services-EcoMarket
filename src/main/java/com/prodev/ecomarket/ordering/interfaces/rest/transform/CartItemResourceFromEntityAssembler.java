package com.prodev.ecomarket.ordering.interfaces.rest.transform;

import com.prodev.ecomarket.ordering.domain.model.entities.CartItem;
import com.prodev.ecomarket.ordering.interfaces.rest.resources.CartItemResource;

public class CartItemResourceFromEntityAssembler {
    public static CartItemResource fromEntity(CartItem cartItem) {
        return new CartItemResource(
                cartItem.getId(),
                cartItem.getProduct().getId(),
                cartItem.getQuantity(),
                cartItem.getShoppingCart().getId()
        );
    }
}
