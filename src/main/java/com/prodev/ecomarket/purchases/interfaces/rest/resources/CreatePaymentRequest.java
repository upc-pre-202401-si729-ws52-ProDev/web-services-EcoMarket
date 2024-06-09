package com.prodev.ecomarket.purchases.interfaces.rest.resources;


import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CreatePaymentRequest {
    private Double amount;
    private String description;
    private String method;
    private Long customerId;
}