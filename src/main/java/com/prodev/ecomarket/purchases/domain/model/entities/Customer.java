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
import com.prodev.ecomarket.purchases.domain.model.commands.CreateCustommerCommand;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.util.List;

@Getter
@Data
@Builder
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Customer extends AbstractAggregateRoot<Customer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Setter
    @Column(nullable = false)
    private int loyaltyPoi;

    @Setter
    @Column(nullable = false, length = 100)
    private String name;

    @Setter
    @Column(nullable = false, length = 100)
    private String lastName;

    @Setter
    @Column(nullable = false)
    private int age;

    @Setter
    @Column(nullable = false, length = 100)
    private String address;

    @Setter
    @Column(nullable = false, length = 100)
    private String email;

    @Setter
    @Column(nullable = false, length = 30)
    private String phone;

    public Customer(CreateCustommerCommand command) {
        this.loyaltyPoi = command.loyaltyPoi();
        this.name = command.name();
        this.lastName = command.lastName();
        this.age = command.age();
        this.address = command.address();
        this.email = command.email();
        this.phone = command.phone();
    }

    protected Customer() {

    }
}
