package com.prodev.ecomarket.ordering.application.internal.commandservices;

import com.prodev.ecomarket.donations.infrastructure.persistence.jpa.repositories.ProductRepository;
import com.prodev.ecomarket.ordering.domain.model.aggregates.ShoppingCart;
import com.prodev.ecomarket.ordering.domain.model.commands.AddCartItemToShoppingCartCommand;
import com.prodev.ecomarket.ordering.domain.model.commands.CreateShoppingCartCommand;
import com.prodev.ecomarket.ordering.domain.model.entities.CartItem;
import com.prodev.ecomarket.ordering.domain.services.CartItemHandleService;
import com.prodev.ecomarket.ordering.domain.services.ShoppingCartHandleService;
import com.prodev.ecomarket.ordering.infrastructure.persistence.jpa.repositories.CartItemRepository;
import com.prodev.ecomarket.ordering.infrastructure.persistence.jpa.repositories.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
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
    @Transactional
    public Optional<CartItem> handle(AddCartItemToShoppingCartCommand command) {
        try {
            var productQuery = productRepository.findById(command.productId());
            if (productQuery.isEmpty()) throw new IllegalArgumentException("Product not found");

            var shoppingCartQuery = shoppingCartRepository.findById(command.shoppingCartId());
            if (shoppingCartQuery.isEmpty()) throw new IllegalArgumentException("Shopping cart not found");

            var shoppingCart = shoppingCartQuery.get();
            var product = productQuery.get();

            var cartItem =  cartItemHandleService.createCartItem(command.quantity(), product, shoppingCart);

            if (cartItem.isPresent()) {
                shoppingCart.getCartItems().add(cartItem.get());
                shoppingCart.calculateTotal();
                shoppingCartRepository.save(shoppingCart);
            }
            return cartItem;
        } catch (Exception e) {
            // Aquí puedes registrar el error y posiblemente lanzar una excepción personalizada
            log.error("Error handling AddCartItemToShoppingCartCommand", e);
            throw new RuntimeException("Error adding cart item", e);
        }
    }

    @Override
    public Optional<ShoppingCart> handle(CreateShoppingCartCommand command) {
        var shoppingCart = new ShoppingCart();
        try{
            shoppingCartRepository.save(shoppingCart);
            return Optional.of(shoppingCart);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving shopping cart");
        }
    }


}
