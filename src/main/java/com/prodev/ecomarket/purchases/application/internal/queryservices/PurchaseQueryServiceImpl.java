package com.prodev.ecomarket.purchases.application.internal.queryservices;

import com.prodev.ecomarket.purchases.domain.model.aggregates.Purchase;
import com.prodev.ecomarket.purchases.domain.model.queries.GetAllPurchasesQuery;
import com.prodev.ecomarket.purchases.domain.model.queries.GetPurchasesByCustomerIdQuery;
import com.prodev.ecomarket.purchases.domain.services.PurchaseQueryService;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseQueryServiceImpl implements PurchaseQueryService {
    private final PurchaseRepository purchaseRepository;

    public PurchaseQueryServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public List<Purchase> handle(GetAllPurchasesQuery query) {
        return purchaseRepository.findAll();
    }

    @Override
    public List<Purchase> handle(GetPurchasesByCustomerIdQuery query) {
        return purchaseRepository.findByCustomerId(query.customerId());
    }
}
