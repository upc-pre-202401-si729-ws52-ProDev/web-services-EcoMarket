package com.prodev.ecomarket.purchases.interfaces.rest.transform;

import com.prodev.ecomarket.purchases.domain.model.aggregates.Payments;
import com.prodev.ecomarket.purchases.interfaces.rest.resources.PaymentResource;

public class PaymentResourceFromEntityAssembler {
    public static PaymentResource toResourceFromEntity(Payments entity) {
        return new PaymentResource(
            entity.getId(),
            entity.getMethod(),
            entity.getStatus(),
            entity.getCreatedAt().toString(),
            entity.getUpdatedAt().toString(),
            entity.getAmount()
        );
    }
}
