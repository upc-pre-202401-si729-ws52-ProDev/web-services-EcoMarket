package com.prodev.ecomarket.donations.interfaces.rest.resources;

public record CompanyResource(
        Long id,
        String name,
        String ruc,
        String aboutDescription
) {
}
