package com.prodev.ecomarket.purchases.interfaces.rest.resources;

import com.prodev.ecomarket.purchases.domain.model.entities.Customer;

import java.util.Date;

public record PurchaseResource(
        Long id,
        Date date,
        double totalAmount,
        String status,
        String paymentMethod,
        Long customerId,
        Long paymentId
) {
}
