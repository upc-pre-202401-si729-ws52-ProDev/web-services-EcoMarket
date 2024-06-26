package com.prodev.ecomarket.purchases.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.prodev.ecomarket.donations.domain.model.valueobjects.Profile;
import com.prodev.ecomarket.ordering.domain.model.aggregates.ShoppingCart;
import com.prodev.ecomarket.purchases.domain.model.aggregates.Payments;
import com.prodev.ecomarket.purchases.domain.model.aggregates.Purchase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Data
@Builder
@AllArgsConstructor
@Entity
public class Customer extends Profile {

    @Column(nullable = false, length = 200)
    private String address;

    @OneToMany(mappedBy = "customer")
    @JsonBackReference
    private List<Payments> payments;

    @OneToMany(mappedBy = "customer")
    @JsonBackReference
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "customer")
    @JsonBackReference
    private List<ShoppingCart> shoppingCarts;

    @Column(nullable = false, length = 9)
    private int loyaltyPoi;

    public Customer() {

    }
}
