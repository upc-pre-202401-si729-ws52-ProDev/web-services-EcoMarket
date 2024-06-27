package com.prodev.ecomarket.purchases.application.internal.queryservices;

import com.prodev.ecomarket.purchases.domain.model.aggregates.Payments;
import com.prodev.ecomarket.purchases.domain.model.queries.GetPaymentsByCustomerIdQuery;
import com.prodev.ecomarket.purchases.domain.model.queries.GetPaymentsByStatusQuery;
import com.prodev.ecomarket.purchases.domain.model.queries.GetAllPaymentsQuery;
import com.prodev.ecomarket.purchases.domain.services.PaymentQueryService;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentsQueryServiceImpl implements PaymentQueryService {

    private final PaymentRepository paymentRepository;

    public PaymentsQueryServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payments> handle(GetPaymentsByCustomerIdQuery query) {
        return paymentRepository.findByCustomerId(query.customerId());
    }
/*
    @Override
    public List<Payments> handle(GetPaymentsByStatusQuery query) {
        return paymentRepository.findByStatus(query.status());
    }*/

    @Override
    public List<Payments> handle(GetAllPaymentsQuery query) {

        return paymentRepository.findAll();
    }
}
