package com.prodev.ecomarket.ordering.domain.model.aggregates;

/**
 * Represents a shopping cart.
 * Contains a list of cart items.
 * Operations include add, remove and update items.
 */

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.prodev.ecomarket.ordering.domain.model.commands.CreateShoppingCartCommand;
import com.prodev.ecomarket.ordering.domain.model.entities.CartItem;
import com.prodev.ecomarket.ordering.domain.model.events.ShoppingCartFinalizedEvent;
import com.prodev.ecomarket.purchases.domain.model.entities.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.DomainEvents;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private final List<Object> domainEvents = new ArrayList<>();

    //TODO: Add a customer field to link the shopping cart to a customer
    //Relationships
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private double total;

    private String status;

    @Getter
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CartItem> cartItems = new ArrayList<>();

    public ShoppingCart (CreateShoppingCartCommand command){
        this.customer = command.customer() ;
        this.status = "OPEN";
        this.total = 0;
    }

    public void calculateTotal() {
        this.total = this.cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public void finalizeCart() {
        this.status = "FINALIZED";
    }


    public void registerEvent(ShoppingCartFinalizedEvent event) {
        this.domainEvents.add(event);
    }

    @DomainEvents
    Collection<Object> domainEvents() {
        return this.domainEvents;
    }

    public void clearCart(){
        this.cartItems.clear();
        this.total = 0;
    }
}
