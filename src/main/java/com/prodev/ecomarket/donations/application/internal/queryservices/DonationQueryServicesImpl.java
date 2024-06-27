package com.prodev.ecomarket.donations.application.internal.queryservices;

import com.prodev.ecomarket.donations.domain.model.aggregates.Donation;
import com.prodev.ecomarket.donations.domain.model.queries.GetAllDonationsQuery;
import com.prodev.ecomarket.donations.domain.model.queries.GetDonationsByCompanyQuery;
import com.prodev.ecomarket.donations.domain.model.queries.GetDonationsByOngQuery;
import com.prodev.ecomarket.donations.domain.services.DonationQueryService;
import com.prodev.ecomarket.donations.infrastructure.persistence.jpa.repositories.DonationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationQueryServicesImpl implements DonationQueryService {
    private final DonationRepository donationRepository;

    public DonationQueryServicesImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public List<Donation> handle(GetAllDonationsQuery query) {
        return donationRepository.findAll();
    }

    @Override
    public List<Donation> handle(GetDonationsByCompanyQuery query) {
        return donationRepository.findByCompanyId(query.company().getId());
    }

    @Override
    public List<Donation> handle(GetDonationsByOngQuery query) {
        return donationRepository.findByOng(query.ong());
    }

    @Override
    public List<Donation> handle(Long companyId) {
        return donationRepository.findByCompanyId(companyId);
    }
}
