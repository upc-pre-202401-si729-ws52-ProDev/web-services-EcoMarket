package com.prodev.ecomarket.purchases.interfaces.rest.transform;

import com.prodev.ecomarket.purchases.domain.model.aggregates.Payments;
import com.prodev.ecomarket.purchases.interfaces.rest.resources.PaymentResponse;

public class PaymentResourceFromEntityAssembler {
    public static PaymentResponse toResourceFromEntity(Payments entity) {
        return new PaymentResponse(
            entity.getId(),
            entity.getMethod(),
            entity.getStatus(),
            entity.getCreatedAt().toString(),
            entity.getUpdatedAt().toString(),
            entity.getAmount()
        );
    }
}
