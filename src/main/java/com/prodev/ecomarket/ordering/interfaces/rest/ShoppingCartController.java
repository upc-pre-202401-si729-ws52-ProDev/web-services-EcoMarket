package com.prodev.ecomarket.ordering.interfaces.rest;

import com.prodev.ecomarket.ordering.domain.model.aggregates.ShoppingCart;
import com.prodev.ecomarket.ordering.domain.model.commands.AddCartItemToShoppingCartCommand;
import com.prodev.ecomarket.ordering.domain.model.entities.CartItem;
import com.prodev.ecomarket.ordering.domain.model.queries.GetAllCartItemsFromShoppingCartQuery;
import com.prodev.ecomarket.ordering.domain.services.ShoppingCartHandleService;
import com.prodev.ecomarket.ordering.domain.services.ShoppingCartQueryService;
import com.prodev.ecomarket.ordering.infrastructure.persistence.jpa.repositories.ShoppingCartRepository;
import com.prodev.ecomarket.ordering.interfaces.rest.resources.CartItemResource;
import com.prodev.ecomarket.ordering.interfaces.rest.resources.CreateCartItemResource;
import com.prodev.ecomarket.ordering.interfaces.rest.resources.CreateShoppingCartResource;
import com.prodev.ecomarket.ordering.interfaces.rest.resources.ShoppingCartResource;
import com.prodev.ecomarket.ordering.interfaces.rest.transform.AddCartItemToShoppingCartCommandFromResourceAssembler;
import com.prodev.ecomarket.ordering.interfaces.rest.transform.CartItemResourceFromEntityAssembler;
import com.prodev.ecomarket.ordering.interfaces.rest.transform.CreateShoppingCartCommandFromResourceAssembler;
import com.prodev.ecomarket.ordering.interfaces.rest.transform.ShoppingCartResourceFromEntityAssembler;
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
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartController(ShoppingCartHandleService shoppingCartHandleService, ShoppingCartQueryService shoppingCartQueryService, ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartHandleService = shoppingCartHandleService;
        this.shoppingCartQueryService = shoppingCartQueryService;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @GetMapping("/{shoppingCartId}")
    public ResponseEntity<List<CartItem>> getCartItemsFromShoppingCart(@PathVariable Long shoppingCartId) {
        List<CartItem> cartItems = shoppingCartQueryService.handle(new GetAllCartItemsFromShoppingCartQuery(shoppingCartId));
        return ResponseEntity.ok(cartItems);
    }

    @PostMapping("/{shoppingCartId}")
    public ResponseEntity<CartItemResource> addCartItemToShoppingCart(@PathVariable Long shoppingCartId, @RequestBody CreateCartItemResource resource) {
        var addCartItemToShoppingCartCommand = AddCartItemToShoppingCartCommandFromResourceAssembler.fromResource(resource);
        CartItem saveCartItem = shoppingCartHandleService.handle(addCartItemToShoppingCartCommand).orElseThrow(() -> new IllegalArgumentException("Error adding cart item"));
        var cartItemResource = CartItemResourceFromEntityAssembler.fromEntity(saveCartItem);
        return ResponseEntity.ok(cartItemResource);

    }

    @PostMapping("/create")
    public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody CreateShoppingCartResource resource) {
        var createShoppingCartCommand = CreateShoppingCartCommandFromResourceAssembler.fromResource(resource);
        ShoppingCart saveShoppingCart = shoppingCartHandleService.handle(createShoppingCartCommand).orElseThrow(() -> new IllegalArgumentException("Error creating shopping cart"));
        return ResponseEntity.ok(saveShoppingCart);
    }

    @GetMapping
    public ResponseEntity<ShoppingCartResource> getShoppingCart(@RequestParam Long shoppingCartId) {
        var shoppingCartEntity = shoppingCartRepository.findById(shoppingCartId).orElseThrow(() -> new IllegalArgumentException("Shopping cart not found"));

        var shoppingCartResource = ShoppingCartResourceFromEntityAssembler.fromEntity(shoppingCartEntity);
        return ResponseEntity.ok(shoppingCartResource);
    }
}
