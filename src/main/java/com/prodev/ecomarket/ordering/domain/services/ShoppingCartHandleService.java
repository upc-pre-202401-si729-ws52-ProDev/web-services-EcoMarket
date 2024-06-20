package com.prodev.ecomarket.ordering.domain.services;

import com.prodev.ecomarket.ordering.domain.model.aggregates.ShoppingCart;
import com.prodev.ecomarket.ordering.domain.model.commands.AddCartItemToShoppingCartCommand;
import com.prodev.ecomarket.ordering.domain.model.commands.CreateShoppingCartCommand;
import com.prodev.ecomarket.ordering.domain.model.entities.CartItem;

import java.util.Optional;

public interface ShoppingCartHandleService {
    Optional<CartItem> handle(AddCartItemToShoppingCartCommand command);

    Optional<ShoppingCart> handle(CreateShoppingCartCommand command);
}
