package com.prodev.ecomarket.purchases.domain.services;

import com.prodev.ecomarket.purchases.domain.model.aggregates.Purchase;
import com.prodev.ecomarket.purchases.domain.model.queries.GetAllPurchasesQuery;
import com.prodev.ecomarket.purchases.domain.model.queries.GetPurchasesByCustomerIdQuery;

import java.util.List;

public interface PurchaseQueryService {
    List<Purchase> handle(GetPurchasesByCustomerIdQuery query);
    List<Purchase> handle(GetAllPurchasesQuery query);
}
