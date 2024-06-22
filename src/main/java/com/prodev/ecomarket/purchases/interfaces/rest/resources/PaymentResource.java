package com.prodev.ecomarket.purchases.interfaces.rest.resources;

public record PaymentResource(
        Long id,
        Double amount,
        String description,
        String method
        //String status,
        ) {

}
