package com.prodev.ecomarket.donations.domain.model.aggregates;

/**
 * Donation Aggregate
 * Represents a donation made by a company to a ONG
 */


import com.prodev.ecomarket.donations.domain.model.commands.CreateDonationCommand;
import com.prodev.ecomarket.donations.domain.model.entities.Company;
import com.prodev.ecomarket.donations.domain.model.entities.Product;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Donation extends AbstractAggregateRoot<Donation> {

    /**The donation id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    /**The donation creation date*/
    @Getter
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    /**The donation quantity of products.*/
    @Column(nullable = false,updatable = true)
    @Getter
    private Integer quantity;

    /**The donation description*/
    @Column(nullable = true, length = 200)
    @Getter
    private String description;

    /**The ONG destination of the donation*/
    @Column(nullable = false, length = 100, updatable = false)
    @Getter
    private String ong;

    /**The product donated*/

    @Getter
    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false,
            foreignKey = @ForeignKey(name = "FK_donation_product"))
    private Product product;

    /**The company that made the donation*/
    @Getter
    @ManyToOne
    @JoinColumn(name = "company_id",nullable = false,
            foreignKey = @ForeignKey(name = "FK_donation_company"))
    private Company company;


    protected Donation() {
    }

    public Donation (CreateDonationCommand command){
        this.quantity = command.quantity();
        this.description = command.description();
        this.ong = command.ong();
        this.company = command.company();
        this.product = command.product();
    }
}
