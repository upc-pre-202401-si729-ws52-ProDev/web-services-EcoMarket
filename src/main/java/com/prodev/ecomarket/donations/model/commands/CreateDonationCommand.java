package com.prodev.ecomarket.donations.model.commands;

import com.prodev.ecomarket.donations.model.entities.Product;

public record CreateDonationCommand(Integer quantity, String description, String ong, Long productId, Long companyId) {
}
