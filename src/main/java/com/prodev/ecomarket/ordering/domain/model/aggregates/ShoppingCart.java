package com.prodev.ecomarket.ordering.domain.model.aggregates;

/**
 * Represents a shopping cart.
 * Contains a list of cart items.
 * Operations include add, remove and update items.
 */

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.prodev.ecomarket.ordering.domain.model.entities.CartItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO: Add a customer field to link the shopping cart to a customer

    private double total;

    @Getter
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CartItem> cartItems = new ArrayList<>();

    public void calculateTotal() {
        this.total = this.cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

}
