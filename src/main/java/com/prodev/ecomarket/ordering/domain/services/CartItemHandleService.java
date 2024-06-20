package com.prodev.ecomarket.ordering.domain.services;

import com.prodev.ecomarket.donations.domain.model.entities.Product;
import com.prodev.ecomarket.ordering.domain.model.aggregates.ShoppingCart;
import com.prodev.ecomarket.ordering.domain.model.entities.CartItem;

import java.util.Optional;

public interface CartItemHandleService {

    Optional<CartItem> createCartItem(int quantity, Product product, ShoppingCart shoppingCart);
}
