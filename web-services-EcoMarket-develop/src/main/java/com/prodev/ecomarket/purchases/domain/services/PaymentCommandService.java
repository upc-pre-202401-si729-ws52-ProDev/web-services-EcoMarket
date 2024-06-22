package com.prodev.ecomarket.purchases.domain.services;

import com.prodev.ecomarket.purchases.domain.model.aggregates.Payments;
import com.prodev.ecomarket.purchases.domain.model.commands.CreatePaymentsCommand;

import java.util.Optional;

public interface PaymentCommandService {
    Optional<Payments> handle(CreatePaymentsCommand command);
}
