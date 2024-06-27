package com.prodev.ecomarket.ordering.domain.model.events;

public record ShoppingCartFinalizedEvent(Long shoppingCartId, Long paymentId, Long purchaseId) {
}