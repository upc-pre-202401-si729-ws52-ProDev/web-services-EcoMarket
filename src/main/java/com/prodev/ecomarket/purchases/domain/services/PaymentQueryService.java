package com.prodev.ecomarket.purchases.domain.services;

import com.prodev.ecomarket.purchases.domain.model.aggregates.Payments;
import com.prodev.ecomarket.purchases.domain.model.queries.GetPaymentsByCustomerIdQuery;
import com.prodev.ecomarket.purchases.domain.model.queries.GetAllPaymentsQuery;
import com.prodev.ecomarket.purchases.domain.model.queries.GetPaymentsByStatusQuery;


import java.util.List;

public interface PaymentQueryService {
    List<Payments> handle(GetPaymentsByCustomerIdQuery query);
   // List<Payments> handle(GetPaymentsByStatusQuery query);
    List<Payments> handle(GetAllPaymentsQuery query);
}
