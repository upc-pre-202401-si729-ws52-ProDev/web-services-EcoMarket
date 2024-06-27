package com.prodev.ecomarket.donations.domain.model.commands;

public record UpdateCompanyCommand(Long id, String ruc, String aboutDescription) {
}
