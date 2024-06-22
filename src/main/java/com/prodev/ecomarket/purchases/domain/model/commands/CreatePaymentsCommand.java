package com.prodev.ecomarket.purchases.domain.model.commands;

public record CreatePaymentsCommand(Double amount, String description, String method, Long customerId) {
}
