package com.prodev.ecomarket.ordering.domain.services;

import com.prodev.ecomarket.ordering.domain.model.aggregates.ShoppingCart;
import com.prodev.ecomarket.ordering.domain.model.commands.AddCartItemToShoppingCart;
import com.prodev.ecomarket.ordering.domain.model.commands.CreateShoppingCart;
import com.prodev.ecomarket.ordering.domain.model.entities.CartItem;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartHandleService {
    Optional<CartItem> handle(AddCartItemToShoppingCart command);

    Optional<ShoppingCart> handle(CreateShoppingCart command);
}
