package com.prodev.ecomarket.donations.domain.services;

import com.prodev.ecomarket.donations.domain.model.aggregates.Donation;
import com.prodev.ecomarket.donations.domain.model.commands.CreateDonationCommand;

import java.util.Optional;

public interface DonationCommandService {
    Optional<Donation> handle(CreateDonationCommand command);
    Donation handle(Long companyId, CreateDonationCommand createDonationCommand);

}
