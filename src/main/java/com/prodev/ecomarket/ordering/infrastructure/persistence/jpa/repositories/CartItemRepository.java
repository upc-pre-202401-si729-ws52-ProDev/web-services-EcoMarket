package com.prodev.ecomarket.ordering.infrastructure.persistence.jpa.repositories;

import com.prodev.ecomarket.donations.domain.model.entities.Product;
import com.prodev.ecomarket.ordering.domain.model.aggregates.ShoppingCart;
import com.prodev.ecomarket.ordering.domain.model.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

    Optional<CartItem> findByProductAndShoppingCart(Product product, ShoppingCart shoppingCart);
}
