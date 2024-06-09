package com.prodev.ecomarket.purchases.interfaces.rest.resources;

public record PaymentResponse(Long id, String amount, String description, String method, String status, Double createdAt) {

}
