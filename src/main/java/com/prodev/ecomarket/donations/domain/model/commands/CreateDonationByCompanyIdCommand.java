package com.prodev.ecomarket.donations.domain.model.commands;

public record CreateDonationByCompanyIdCommand(Long companyId, CreateDonationCommand createDonationCommand) {
}
