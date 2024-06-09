package com.prodev.ecomarket.purchases.domain.services;

import com.prodev.ecomarket.purchases.domain.model.aggregates.Payments;
import com.prodev.ecomarket.purchases.domain.model.queries.FindPaymentsByCustomerIdQuery;
import com.prodev.ecomarket.purchases.domain.model.queries.FindPaymentsByDateRangeQuery;


import java.util.List;

public interface PaymentQueryService {
    List<Payments> handle(FindPaymentsByCustomerIdQuery query);
    List<Payments> handle(FindPaymentsByDateRangeQuery query);
}
