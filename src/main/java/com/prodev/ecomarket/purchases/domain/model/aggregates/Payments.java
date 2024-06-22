package com.prodev.ecomarket.purchases.domain.model.aggregates;

import com.prodev.ecomarket.purchases.domain.model.commands.CreatePaymentsCommand;
import com.prodev.ecomarket.purchases.domain.model.valueobjects.PaymentStatus;
import com.prodev.ecomarket.purchases.infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.prodev.ecomarket.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import com.prodev.ecomarket.purchases.domain.model.entities.Customer;
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

/*    @Column(nullable = false, length = 50)
    @Getter
    private PaymentStatus status;*/

    @Column(nullable = false, length = 100)
    @Getter
    private String description;

    @Column(nullable = false, length = 50)
    @Getter
    private String method;

    /** The customer who made the payment */
    @Getter
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_payment_customer"))
    private Customer customer;


    public Payments() {
    }

    public Payments(CreatePaymentsCommand command, Customer customer) {
        this.amount = command.amount();
      //  this.status = status;
        this.description = command.description();
        this.method = command.method();
        this.customer= customer;
    }

   /* public void setStatus(PaymentStatus status) {
        this.status = status;
    }*/
/*
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    private PaymentsStatus status;

    public Payments(){
    }

    public Payments(Long id){
        this.id = id;
        this.status = PaymentsStatus.REQUESTED;
    }

    public void confirm(){
        this.status = PaymentsStatus.CONFIRMED;
    }

    public void cancel(){
        this.status = PaymentsStatus.CANCELED;
    }

    public boolean isConfirmed(){
        return this.status == PaymentsStatus.CONFIRMED;
    }

    public boolean isCanceled(){
        return this.status == PaymentsStatus.CANCELED;
    }

    public String getStatus(){
        return this.status.name().toLowerCase();
    }
*/
}
