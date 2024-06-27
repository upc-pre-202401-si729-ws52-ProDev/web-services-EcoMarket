package com.prodev.ecomarket.purchases.interfaces.rest.resources;

import java.util.Date;

public record CreatePurchaseResource(Date date,
                                     double totalAmount,
                                     String status,
                                     String paymentMethod,
                                     Long customerId,
                                     int paymentId) {
}
