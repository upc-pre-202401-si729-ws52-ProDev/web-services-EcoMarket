package com.prodev.ecomarket.Review.Domain.Models.Aggregates;

import com.prodev.ecomarket.Review.Domain.Models.Commands.CreaterReviewCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Review extends AbstractAggregateRoot<Review> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int calification;

    @Column(nullable = false, updatable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String product;

    protected Review() {}

    public Review(CreaterReviewCommand command) {
        this.content = command.content();
        this.calification = command.calification();
        this.product = command.product();
        this.date = LocalDate.now(); // Se genera automáticamente al crear la review
    }
}