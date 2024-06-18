package com.prodev.ecomarket.purchases.domain.model.queries;

import com.prodev.ecomarket.purchases.domain.model.valueobjects.PaymentStatus;

import java.util.Date;

public record GetPaymentsByStatusQuery(PaymentStatus status) {
}
