package com.prodev.ecomarket.ordering.interfaces.rest;

import com.prodev.ecomarket.ordering.domain.model.aggregates.ShoppingCart;
import com.prodev.ecomarket.ordering.domain.model.commands.AddCartItemToShoppingCart;
import com.prodev.ecomarket.ordering.domain.model.commands.CreateShoppingCart;
import com.prodev.ecomarket.ordering.domain.model.entities.CartItem;
import com.prodev.ecomarket.ordering.domain.model.queries.GetAllCartItemsFromShoppingCartQuery;
import com.prodev.ecomarket.ordering.domain.services.ShoppingCartHandleService;
import com.prodev.ecomarket.ordering.domain.services.ShoppingCartQueryService;
import com.prodev.ecomarket.ordering.interfaces.rest.resources.CreateShoppingCartResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shopping-cart")
@Tag(name = "Shopping Cart")
public class ShoppingCartController {
    private final ShoppingCartHandleService shoppingCartHandleService;
    private final ShoppingCartQueryService shoppingCartQueryService;

    public ShoppingCartController(ShoppingCartHandleService shoppingCartHandleService, ShoppingCartQueryService shoppingCartQueryService) {
        this.shoppingCartHandleService = shoppingCartHandleService;
        this.shoppingCartQueryService = shoppingCartQueryService;
    }

    @GetMapping("/{shoppingCartId}")
    public ResponseEntity<List<CartItem>> getCartItemsFromShoppingCart(@PathVariable Long shoppingCartId) {
        List<CartItem> cartItems = shoppingCartQueryService.handle(new GetAllCartItemsFromShoppingCartQuery(shoppingCartId));
        return ResponseEntity.ok(cartItems);
    }

    @PostMapping("/{shoppingCartId}")
    public ResponseEntity<CartItem> addCartItemToShoppingCart(@PathVariable Long shoppingCartId, @RequestBody AddCartItemToShoppingCart command) {
        var AddCardItemCommand = new AddCartItemToShoppingCart(command.quantity(), command.productId());
        CartItem savedCartItem = shoppingCartHandleService.handle(command).orElseThrow(() -> new IllegalArgumentException("Error adding cart item to shopping cart"));
        return ResponseEntity.ok(savedCartItem);
    }

    @PostMapping("/create")
    public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody CreateShoppingCartResource resource) {
        var createShoppingCartCommand = new CreateShoppingCart();
        ShoppingCart saveShoppingCart = shoppingCartHandleService.handle(createShoppingCartCommand).orElseThrow(() -> new IllegalArgumentException("Error creating shopping cart"));
        return ResponseEntity.ok(saveShoppingCart);
    }
}
