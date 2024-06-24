package com.prodev.ecomarket.donations.domain.model.commands;

import com.prodev.ecomarket.donations.domain.model.entities.Company;
import com.prodev.ecomarket.donations.domain.model.entities.Product;

public record CreateDonationCommand(Integer quantity, String description,
                                    String ong, Product product, Company company) {
}
