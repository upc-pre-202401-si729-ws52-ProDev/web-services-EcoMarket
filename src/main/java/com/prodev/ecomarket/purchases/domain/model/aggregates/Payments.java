package com.prodev.ecomarket.purchases.domain.model.aggregates;

import com.prodev.ecomarket.ordering.domain.model.aggregates.ShoppingCart;
import com.prodev.ecomarket.purchases.domain.model.commands.CreatePaymentsCommand;
import com.prodev.ecomarket.purchases.domain.model.valueobjects.PaymentStatus;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.prodev.ecomarket.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import com.prodev.ecomarket.purchases.domain.model.entities.Customer;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


@Entity
public class Payments extends AuditableAbstractAggregateRoot<Payments> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Column(nullable = false)
    @Getter
    private Double amount;

    @Column(nullable = false, length = 50)
    @Getter
    private PaymentStatus status;

    @Column(nullable = false, length = 100)
    @Getter
    private String description;

    @Column(nullable = false, length = 50)
    @Getter
    private String method;

    /** The customer who made the payment */
    @Getter
    @ManyToOne
    @JoinColumn(name = "customer_id",
            foreignKey = @ForeignKey(name = "FK_payment_customer"))
    private Customer customer;


    public Payments() {
    }

    public Payments(CreatePaymentsCommand command, Customer customer) {
        this.amount = command.amount();
        this.status = status;
        this.description = command.description();
        this.method = command.method();
        this.customer= customer;
    }

    public Payments(ShoppingCart cart){
        this.amount = cart.getTotal();
        this.status = PaymentStatus.PENDING;
        this.description = "Payment for shopping cart " + cart.getId();
        this.customer = cart.getCustomer();
        this.method = "Credit Card";
    }

   public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Payments(Long id){
        this.id = id;
        this.status = PaymentStatus.PENDING;
    }

    public void confirm(){
        this.status = PaymentStatus.PAID;
    }

    public void cancel(){
        this.status = PaymentStatus.FAILED;
    }

    public boolean isConfirmed(){
        return this.status == PaymentStatus.PAID;
    }

    public boolean isCanceled(){
        return this.status == PaymentStatus.FAILED;
    }

    public String getStatus(){
        return this.status.name().toLowerCase();
    }

}
