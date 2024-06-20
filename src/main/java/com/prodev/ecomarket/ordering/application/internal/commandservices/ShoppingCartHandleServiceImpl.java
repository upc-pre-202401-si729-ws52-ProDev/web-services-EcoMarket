package com.prodev.ecomarket.ordering.application.internal.commandservices;

import com.prodev.ecomarket.donations.domain.model.entities.Product;
import com.prodev.ecomarket.donations.infrastructure.persistence.jpa.repositories.ProductRepository;
import com.prodev.ecomarket.ordering.domain.model.aggregates.ShoppingCart;
import com.prodev.ecomarket.ordering.domain.model.commands.AddCartItemToShoppingCart;
import com.prodev.ecomarket.ordering.domain.model.commands.CreateShoppingCart;
import com.prodev.ecomarket.ordering.domain.model.entities.CartItem;
import com.prodev.ecomarket.ordering.domain.services.CartItemHandleService;
import com.prodev.ecomarket.ordering.domain.services.ShoppingCartHandleService;
import com.prodev.ecomarket.ordering.infrastructure.persistence.jpa.repositories.CartItemRepository;
import com.prodev.ecomarket.ordering.infrastructure.persistence.jpa.repositories.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartHandleServiceImpl implements ShoppingCartHandleService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartItemHandleService cartItemHandleService;

    public ShoppingCartHandleServiceImpl( ShoppingCartRepository shoppingCartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository, CartItemHandleService cartItemHandleService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.cartItemHandleService = cartItemHandleService;
    }

    @Override
    public Optional<CartItem> handle(AddCartItemToShoppingCart command) {
        var productQuery = productRepository.findById(command.productId());

        var cartItem = cartItemHandleService.createCartItem(command.quantity(), productQuery.get());
        try{
            shoppingCartRepository.save(cartItem.get().getShoppingCart());
            return Optional.of(cartItem.get());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving cart item");
        }
    }

    @Override
    public Optional<ShoppingCart> handle(CreateShoppingCart command) {
        var shoppingCart = new ShoppingCart();
        try{
            shoppingCartRepository.save(shoppingCart);
            return Optional.of(shoppingCart);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving shopping cart");
        }
    }


}
