package com.prodev.ecomarket.ordering.application.internal.commandservices;

import com.prodev.ecomarket.donations.domain.model.entities.Product;
import com.prodev.ecomarket.donations.infrastructure.persistence.jpa.repositories.ProductRepository;
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
    public Optional<CartItem> createCartItem(int quantity, Product product) {
        var productQuery = productRepository.findById(product.getId());

        if (productQuery.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }

        var cartItem = new CartItem(product, quantity);
        //TODO: FIx this error. It is because the cartItem is not being saved. Make good
        //resources and transforms to save the cartItem
        try{
            cartItemRepository.save(cartItem);
            return Optional.of(cartItem);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving cart item");
        }
    }
}
