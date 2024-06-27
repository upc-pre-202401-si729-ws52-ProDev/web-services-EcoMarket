package com.prodev.ecomarket.purchases.domain.model.commands;

public record CreatePurchaseCommand (Double totalAmount, String status, String paymentMethod,
                                        Long customerId, int productId) {
}
