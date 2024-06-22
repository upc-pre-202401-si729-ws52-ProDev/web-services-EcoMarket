package com.prodev.ecomarket.donations.domain.model.commands;

public record CreateCompanyCommand(String status,
                                   String name,
                                   String address,
                                   String email,
                                   String phone) {
}
