package com.prodev.ecomarket.ordering.domain.model.commands;

public record AddCartItemToShoppingCartCommand(int quantity, Long productId, Long shoppingCartId) {
}
