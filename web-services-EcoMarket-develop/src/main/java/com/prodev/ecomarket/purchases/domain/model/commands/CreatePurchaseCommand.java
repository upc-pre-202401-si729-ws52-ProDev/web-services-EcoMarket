package com.prodev.ecomarket.purchases.domain.model.commands;

import com.prodev.ecomarket.purchases.domain.model.entities.Customer;

public record CreatePurchaseCommand (Double totalAmount, String status, String paymentMethod, int customerId, int productId) {
}
