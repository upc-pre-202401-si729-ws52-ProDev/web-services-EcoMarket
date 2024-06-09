package com.prodev.ecomarket.purchases.application.internal.commandservices;

import com.prodev.ecomarket.purchases.domain.model.commands.CreatePaymentsCommand;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentsCommandServiceImpl implements PaymentsCommandService {

    private final PaymentRepository paymentRepository;

    public PaymentsCommandServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void createPayment(CreatePaymentsCommand command) {

    }
}

