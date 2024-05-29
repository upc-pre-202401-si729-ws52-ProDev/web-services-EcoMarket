package com.prodev.ecomarket.donations.model.valueobjects;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String email;

    private String phone;
}