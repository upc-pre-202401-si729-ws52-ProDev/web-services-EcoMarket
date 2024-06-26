package com.prodev.ecomarket.purchases.domain.model.aggregates;

import com.prodev.ecomarket.ordering.domain.model.aggregates.ShoppingCart;
import com.prodev.ecomarket.purchases.domain.model.commands.CreatePurchaseCommand;
import com.prodev.ecomarket.purchases.domain.model.entities.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Purchase extends AbstractAggregateRoot<Purchase> {

    //**The purchase id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    //**The purchase creation date. */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    //**The purchase total amount. */
    @Column(nullable = false,updatable = true)
    @Getter
    private Double totalAmount;

    //**The purchase status. */
    @Column(nullable = false, length = 30)
    @Getter
    private String status;

    //**The purchase payment method. */
    @Column(nullable = false, length = 50)
    @Getter
    private String paymentMethod;

    //**The customer that made the purchase. */
    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false,
            foreignKey = @ForeignKey(name = "FK_purchase_customer"))
    private Customer customer;

    public Purchase(ShoppingCart cart){
        this.totalAmount = cart.getTotal();
        this.status = "PENDING";
        this.paymentMethod = "CREDIT_CARD";
        this.customer = cart.getCustomer();
    }

    protected Purchase(){}
    public Purchase (CreatePurchaseCommand command){
        this.totalAmount = command.totalAmount();
        this.status = command.status();
        this.paymentMethod = command.paymentMethod();

        /*this.customer = customerRepository.findById(command.customerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        this.products = productRepository.findById(command.productId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));*/
    }
}
