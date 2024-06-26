package com.prodev.ecomarket.ordering.application.internal.commandservices;

import com.prodev.ecomarket.donations.domain.model.entities.Product;
import com.prodev.ecomarket.donations.infrastructure.persistence.jpa.repositories.ProductRepository;
import com.prodev.ecomarket.ordering.domain.model.aggregates.ShoppingCart;
import com.prodev.ecomarket.ordering.domain.model.entities.CartItem;
import com.prodev.ecomarket.ordering.domain.services.CartItemHandleService;
import com.prodev.ecomarket.ordering.infrastructure.persistence.jpa.repositories.CartItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemHandleServiceImpl implements CartItemHandleService {
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartItemHandleServiceImpl(CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public Optional<CartItem> createCartItem(int quantity, Product product, ShoppingCart shoppingCart) {

        Optional<CartItem> existingCartItem = cartItemRepository.findByProductAndShoppingCart(product, shoppingCart);

        if (existingCartItem.isPresent()) {
            // Si ya existe, incrementar la cantidad
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemRepository.save(cartItem);
        } else {
            // Si no existe, crear un nuevo CartItem
            CartItem cartItem = new CartItem(product, quantity, shoppingCart);
            try {
                cartItemRepository.save(cartItem);
                return Optional.of(cartItem);
            } catch (Exception e) {
                throw new IllegalArgumentException("Error saving cart item");
            }
        }

        return existingCartItem;
    }
}
