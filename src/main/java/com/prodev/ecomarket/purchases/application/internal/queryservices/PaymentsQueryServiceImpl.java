package com.prodev.ecomarket.purchases.application.internal.queryservices;

import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.PaymentRepository;
import com.prodev.ecomarket.purchases.interfaces.rest.transform.PaymentResourceFromEntityAssembler;
import com.prodev.ecomarket.purchases.interfaces.rest.resources.PaymentResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentsQueryServiceImpl implements PaymentsQueryService {

    private final PaymentRepository paymentRepository;

    public PaymentsQueryServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentResponse getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .map(PaymentResourceFromEntityAssembler::toResourceFromEntity)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(PaymentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
    }
}
