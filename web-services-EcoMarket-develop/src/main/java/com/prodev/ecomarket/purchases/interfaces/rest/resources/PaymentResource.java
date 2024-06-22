package com.prodev.ecomarket.purchases.interfaces.rest.resources;

public record PaymentResource(
        Long id,
        String amount,
        com.prodev.ecomarket.purchases.domain.model.valueobjects.PaymentStatus description,
        String method,
        String status,
        Double createdAt) {

}
