package com.prodev.ecomarket.purchases.application.internal.commandservices;

import com.prodev.ecomarket.purchases.domain.model.commands.CreatePaymentsCommand;

public interface PaymentsCommandService {
    void createPayment(CreatePaymentsCommand command);
}
