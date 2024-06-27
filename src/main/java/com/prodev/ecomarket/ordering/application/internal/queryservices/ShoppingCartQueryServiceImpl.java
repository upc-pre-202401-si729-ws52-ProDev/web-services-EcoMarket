package com.prodev.ecomarket.ordering.application.internal.queryservices;

import com.prodev.ecomarket.ordering.domain.model.entities.CartItem;
import com.prodev.ecomarket.ordering.domain.model.queries.GetAllCartItemsFromShoppingCartQuery;
import com.prodev.ecomarket.ordering.domain.services.ShoppingCartQueryService;
import com.prodev.ecomarket.ordering.infrastructure.persistence.jpa.repositories.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartQueryServiceImpl implements ShoppingCartQueryService {
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartQueryServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public List<CartItem> handle(GetAllCartItemsFromShoppingCartQuery query) {
        return shoppingCartRepository.findById(query.shoppingCartId()).get().getCartItems();
    }


}
