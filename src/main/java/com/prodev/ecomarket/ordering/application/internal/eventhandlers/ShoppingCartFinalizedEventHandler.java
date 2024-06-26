package com.prodev.ecomarket.ordering.application.internal.eventhandlers;

import com.prodev.ecomarket.ordering.domain.model.events.ShoppingCartFinalizedEvent;

public class ShoppingCartFinalizedEventHandler {

    public void handle(ShoppingCartFinalizedEvent event) {
        //Here is where the logic to handle the event would go
        System.out.println("Shopping cart finalized event handled");
    }
}
