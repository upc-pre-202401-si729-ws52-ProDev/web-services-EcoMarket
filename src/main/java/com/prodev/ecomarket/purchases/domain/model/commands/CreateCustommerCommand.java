package com.prodev.ecomarket.purchases.domain.model.commands;

public record CreateCustommerCommand(int poi,
                                     String name,
                                     String lastname,
                                     int age,
                                     String address,
                                     String email,
                                     String phone) {
}
