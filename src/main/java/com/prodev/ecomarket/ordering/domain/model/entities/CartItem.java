package com.prodev.ecomarket.ordering.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.prodev.ecomarket.donations.domain.model.entities.Product;
import com.prodev.ecomarket.ordering.domain.model.aggregates.ShoppingCart;
import com.prodev.ecomarket.ordering.domain.model.commands.CreateCartItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    @JsonBackReference
    private ShoppingCart shoppingCart;


    public CartItem(Product product, int quantity,ShoppingCart shoppingCart) {
        this.product = product;
        this.quantity = quantity;
        this.shoppingCart = shoppingCart;
    }

    public CartItem(CreateCartItem command) {
        this.quantity = command.quantity();
        this.product = command.product();
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }
}
