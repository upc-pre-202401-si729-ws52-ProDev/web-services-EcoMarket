package com.prodev.ecomarket.donations.interfaces.rest.resources;

public record CreateDonationResource(String ong, String product, String company, Integer quantity, String description ) {
}
