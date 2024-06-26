package com.prodev.ecomarket.donations.domain.model.commands;

public record CreateProductCommand(String name, String description, String type,
                                   Integer quantity, String defect, String urlImage, double price) {
}
