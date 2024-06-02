package com.prodev.ecomarket.donations.domain.model.valueobjects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor

@MappedSuperclass
public abstract class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String name;

    private String address;

    private String email;

    private String phone;
}