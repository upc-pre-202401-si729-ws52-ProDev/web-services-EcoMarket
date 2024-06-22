package com.prodev.ecomarket.purchases.application.internal.commandservices;

import com.prodev.ecomarket.purchases.domain.model.aggregates.Payments;
import com.prodev.ecomarket.purchases.domain.model.commands.CreatePaymentsCommand;
import com.prodev.ecomarket.purchases.domain.model.valueobjects.PaymentStatus;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.PaymentRepository;
import org.springframework.stereotype.Service;
import com.prodev.ecomarket.purchases.domain.services.PaymentCommandService;

import java.util.Optional;

@Service
public class PaymentsCommandServiceImpl implements PaymentCommandService {

    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;

    public PaymentsCommandServiceImpl(PaymentRepository paymentRepository, CustomerRepository customerRepository) {
        this.paymentRepository = paymentRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Payments> handle(CreatePaymentsCommand command) {
        var customer = customerRepository.findById(command.customerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        var payment = new Payments(command, customer, PaymentStatus.PENDING);
        paymentRepository.save(payment);

        boolean paymentSuccessful = processPayment(payment);

        if (paymentSuccessful) {
            payment.setStatus(PaymentStatus.PAID);
        } else {
            payment.setStatus(PaymentStatus.FAILED);
            throw new IllegalArgumentException("Payment processing failed for payment ID: " + payment.getId());
        }

        paymentRepository.save(payment);

        return Optional.of(payment);

    }
    private boolean processPayment(Payments payment) {
        // Simulación: Probabilidad de éxito del 90%
        double random = Math.random();
        // Pago fallido
        return random < 0.9; // Pago exitoso
    }
}

