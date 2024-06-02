package com.prodev.ecomarket.donations.domain.services;

import com.prodev.ecomarket.donations.domain.model.aggregates.Donation;
import com.prodev.ecomarket.donations.domain.model.queries.GetAllDonationsQuery;
import com.prodev.ecomarket.donations.domain.model.queries.GetDonationsByCompanyQuery;
import com.prodev.ecomarket.donations.domain.model.queries.GetDonationsByOngQuery;

import java.util.List;

public interface DonationQueryService {
    List<Donation> handle(GetAllDonationsQuery query);
    List<Donation> handle(GetDonationsByCompanyQuery query );
    List<Donation> handle(GetDonationsByOngQuery query);
}
