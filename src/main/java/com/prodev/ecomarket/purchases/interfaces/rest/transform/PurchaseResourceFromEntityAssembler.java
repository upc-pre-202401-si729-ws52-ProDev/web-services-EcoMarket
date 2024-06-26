package com.prodev.ecomarket.purchases.interfaces.rest.transform;

import com.prodev.ecomarket.purchases.domain.model.aggregates.Purchase;
import com.prodev.ecomarket.purchases.interfaces.rest.resources.PurchaseResource;

public class PurchaseResourceFromEntityAssembler {
    public static PurchaseResource toPurchaseFromEntity(Purchase entity) {
        return new PurchaseResource(
            entity.getId(), null,
                entity.getTotalAmount(),
                entity.getStatus(),
            entity.getPaymentMethod(), null, null
        );
    }
}
