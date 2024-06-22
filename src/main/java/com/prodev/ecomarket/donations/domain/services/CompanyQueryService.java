package com.prodev.ecomarket.donations.domain.services;

import com.prodev.ecomarket.donations.domain.model.entities.Company;
import com.prodev.ecomarket.donations.domain.model.queries.GetCompanyByNameQuery;

import java.util.Optional;

public interface CompanyQueryService {
    Optional<Company> handle(GetCompanyByNameQuery query);
}
