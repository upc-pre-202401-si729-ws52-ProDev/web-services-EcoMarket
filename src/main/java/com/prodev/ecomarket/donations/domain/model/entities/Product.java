package com.prodev.ecomarket.donations.domain.model.entities;

import com.prodev.ecomarket.donations.domain.model.commands.CreateProductCommand;
import com.prodev.ecomarket.iam.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Product {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // En Product.java
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Getter
    @Column(nullable = false, length = 200)
    private String name;

    @Getter
    @Column(nullable = false, length = 200)
    private String description;

    @Getter
    @Column(nullable = false,length = 100)
    private String type;

    @Getter
    @Column(nullable = false)
    private Integer quantity;

    private double price;

    @Getter
    @Column(nullable = false, length = 200)
    private String defect;

    @Getter
    @Column(nullable = false, length = 200)
    private String urlImage;

    public Product(CreateProductCommand command){
        this.name = command.name();
        this.description = command.description();
        this.type = command.type();
        this.quantity = command.quantity();
        this.defect = command.defect();
        this.urlImage = command.urlImage();
        this.price = command.price();
    }

    public Product(CreateProductCommand command, User user){
        this.user = user;
        this.name = command.name();
        this.description = command.description();
        this.type = command.type();
        this.quantity = command.quantity();
        this.defect = command.defect();
        this.urlImage = command.urlImage();
        this.price = command.price();
    }

}
