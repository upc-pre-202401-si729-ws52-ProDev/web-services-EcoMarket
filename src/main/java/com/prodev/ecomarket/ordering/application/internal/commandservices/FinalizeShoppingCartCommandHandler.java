package com.prodev.ecomarket.ordering.application.internal.commandservices;

import com.prodev.ecomarket.ordering.domain.model.aggregates.ShoppingCart;
import com.prodev.ecomarket.ordering.domain.model.commands.FinalizeShoppingCartCommand;
import com.prodev.ecomarket.ordering.domain.model.events.ShoppingCartFinalizedEvent;
import com.prodev.ecomarket.ordering.infrastructure.persistence.jpa.repositories.ShoppingCartRepository;
import com.prodev.ecomarket.purchases.domain.model.aggregates.Payments;
import com.prodev.ecomarket.purchases.domain.model.aggregates.Purchase;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.PaymentRepository;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.PurchaseRepository;

public class FinalizeShoppingCartCommandHandler {
    private final ShoppingCartRepository shoppingCartRepository;
    private final PaymentRepository paymentRepository;
    private final PurchaseRepository purchaseRepository;

    public FinalizeShoppingCartCommandHandler(ShoppingCartRepository shoppingCartRepository, PaymentRepository paymentRepository, PurchaseRepository purchaseRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.paymentRepository = paymentRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public void handle(FinalizeShoppingCartCommand command) {
        ShoppingCart cart = shoppingCartRepository.findById(command.shoppingCartId()).orElseThrow(
                () -> new IllegalArgumentException("Shopping cart not found"));
        Payments payment = new Payments(cart);
        Purchase purchase = new Purchase(cart);

        paymentRepository.save(payment);
        purchaseRepository.save(purchase);

        cart.finalizeCart();
        shoppingCartRepository.save(cart);

        ShoppingCartFinalizedEvent event = new ShoppingCartFinalizedEvent(cart.getId(), payment.getId(), purchase.getId());
        cart.registerEvent(event);
    }
}