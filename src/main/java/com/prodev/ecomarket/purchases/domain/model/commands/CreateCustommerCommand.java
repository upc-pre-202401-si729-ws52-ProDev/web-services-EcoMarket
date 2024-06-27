package com.prodev.ecomarket.purchases.domain.model.commands;

public record CreateCustommerCommand(int loyaltyPoi,
                                     String name,
                                     String lastName,
                                     int age,
                                     String address,
                                     String email,
                                     String phone) {
}
