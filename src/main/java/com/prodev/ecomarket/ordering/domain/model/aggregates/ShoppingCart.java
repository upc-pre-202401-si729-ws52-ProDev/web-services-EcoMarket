package com.prodev.ecomarket.ordering.domain.model.aggregates;

/**
 * Represents a shopping cart.
 * Contains a list of cart items.
 * Operations include add, remove and update items.
 */

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double total;

    @Getter
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();


}
