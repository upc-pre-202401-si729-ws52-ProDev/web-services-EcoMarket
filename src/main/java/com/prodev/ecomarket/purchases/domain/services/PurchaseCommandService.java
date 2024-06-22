package com.prodev.ecomarket.purchases.domain.services;

import com.prodev.ecomarket.purchases.domain.model.aggregates.Purchase;
import com.prodev.ecomarket.purchases.domain.model.commands.CreatePurchaseCommand;

import java.util.Optional;

public interface PurchaseCommandService {
    Optional<Purchase> handle(CreatePurchaseCommand command);
}
