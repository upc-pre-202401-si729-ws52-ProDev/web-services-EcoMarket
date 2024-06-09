package com.prodev.ecomarket.purchases.domain.model.commands;

public record CreatePaymentsCommand(Double amount, String status, String method, Long customerId) {
}
