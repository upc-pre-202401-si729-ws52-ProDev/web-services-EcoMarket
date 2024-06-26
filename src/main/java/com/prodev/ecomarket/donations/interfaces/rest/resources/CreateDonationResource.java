package com.prodev.ecomarket.donations.interfaces.rest.resources;

public record CreateDonationResource(String ong, Long productId, Long companyId, Integer quantity, String description ) {
}
