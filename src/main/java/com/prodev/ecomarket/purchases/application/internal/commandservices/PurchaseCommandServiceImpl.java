package com.prodev.ecomarket.purchases.application.internal.commandservices;

import com.prodev.ecomarket.purchases.domain.model.aggregates.Purchase;
import com.prodev.ecomarket.purchases.domain.model.commands.CreatePurchaseCommand;
import com.prodev.ecomarket.purchases.domain.services.PurchaseCommandService;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseCommandServiceImpl implements PurchaseCommandService{
    private final PurchaseRepository purchaseRepository;

    public PurchaseCommandServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Optional<Purchase> handle(CreatePurchaseCommand command){
        var purchase = new Purchase(command);
        try{
            purchaseRepository.save(purchase);
            return Optional.of(purchase);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving purchase", e);
        }
    }
}
