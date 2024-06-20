package com.prodev.ecomarket.ordering.domain.model.entities;

import com.prodev.ecomarket.donations.domain.model.commands.CreateProductCommand;
import com.prodev.ecomarket.donations.domain.model.entities.Product;
import com.prodev.ecomarket.ordering.domain.model.aggregates.ShoppingCart;
import com.prodev.ecomarket.ordering.domain.model.commands.CreateCartItem;
import com.prodev.ecomarket.ordering.domain.model.commands.CreateShoppingCart;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Product product;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;


    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public CartItem(CreateCartItem command) {
        this.quantity = command.quantity();
        this.product = command.product();
    }
}
