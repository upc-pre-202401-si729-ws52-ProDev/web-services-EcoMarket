package com.prodev.ecomarket.purchases.application.internal.commandservices;

import com.prodev.ecomarket.purchases.domain.model.aggregates.Purchase;
import com.prodev.ecomarket.purchases.domain.model.commands.CreatePurchaseCommand;
import com.prodev.ecomarket.purchases.domain.services.PurchaseCommandService;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseCommandServiceImpl implements PurchaseCommandService{
    private final PurchaseRepository purchaseRepository;
    private final CustomerRepository customerRepository;

    public PurchaseCommandServiceImpl(PurchaseRepository purchaseRepository, CustomerRepository customerRepository) {
        this.purchaseRepository = purchaseRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Purchase> handle(CreatePurchaseCommand command){
        var customer = customerRepository.findById(command.customerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        var purchase = new Purchase(command,customer);
        try{
            purchaseRepository.save(purchase);
            return Optional.of(purchase);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving purchase", e);
        }
    }
}
