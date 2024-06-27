package com.prodev.ecomarket.purchases.interfaces.rest.resources;

public record UpdateCustomerResource(int loyaltyPoi,
                                     String name,
                                     String lastName,
                                     int age,
                                     String address,
                                     String email,
                                     String phone) {
}
