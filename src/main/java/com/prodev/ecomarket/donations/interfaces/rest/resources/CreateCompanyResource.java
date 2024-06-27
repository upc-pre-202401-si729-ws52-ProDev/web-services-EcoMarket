package com.prodev.ecomarket.donations.interfaces.rest.resources;

public record CreateCompanyResource(
        String name,
        String ruc,
        String aboutDescription
) {
}
