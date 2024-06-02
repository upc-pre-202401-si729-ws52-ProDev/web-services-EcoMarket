package com.prodev.ecomarket.donations.application.internal.commandservices;

import com.prodev.ecomarket.donations.domain.model.aggregates.Donation;
import com.prodev.ecomarket.donations.domain.model.commands.CreateDonationCommand;
import com.prodev.ecomarket.donations.domain.services.DonationCommandService;
import com.prodev.ecomarket.donations.infrastructure.persistence.jpa.repositories.DonationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DonationCommandServiceImpl implements DonationCommandService {
    private final DonationRepository donationRepository;

    public DonationCommandServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public Optional<Donation> handle(CreateDonationCommand command) {
        var donation = new Donation(command);
        try {
            donationRepository.save(donation);
            return Optional.of(donation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving donation", e);
        }
    }
}
