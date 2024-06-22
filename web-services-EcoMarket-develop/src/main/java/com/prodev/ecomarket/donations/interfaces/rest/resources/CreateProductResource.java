package com.prodev.ecomarket.donations.interfaces.rest.resources;

public record CreateProductResource(String name, String description, String type, Integer quantity, String defect, String urlImage ) {
}
