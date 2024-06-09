package com.prodev.ecomarket.purchases.application.internal.queryservices;

import com.prodev.ecomarket.purchases.interfaces.rest.resources.PaymentResponse;

import java.util.List;

public interface PaymentsQueryService {
    PaymentResponse getPaymentById(Long id);
    List<PaymentResponse> getAllPayments();
}
