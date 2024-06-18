package com.prodev.ecomarket.purchases.interfaces.rest.resources;


import lombok.Getter;
import lombok.Setter;

public record CreatePaymentResource(
        Double amount,
        String description,
        String method,
        Long customerId
) {

}