package com.prodev.ecomarket.ordering.domain.services;

import com.prodev.ecomarket.ordering.domain.model.entities.CartItem;
import com.prodev.ecomarket.ordering.domain.model.queries.GetAllCartItemsFromShoppingCartQuery;

import java.util.List;

public interface ShoppingCartQueryService {
    List<CartItem> handle(GetAllCartItemsFromShoppingCartQuery query);

}
