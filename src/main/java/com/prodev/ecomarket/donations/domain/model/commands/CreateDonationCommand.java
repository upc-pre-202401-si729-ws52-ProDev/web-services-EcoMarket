package com.prodev.ecomarket.donations.domain.model.commands;

public record CreateDonationCommand(Integer quantity, String description, String ong, Long productId, Long companyId) {
}
