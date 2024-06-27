package com.prodev.ecomarket.donations.domain.services;

import com.prodev.ecomarket.donations.domain.model.entities.Company;
import com.prodev.ecomarket.donations.domain.model.queries.GetAllCompanysQuery;
import com.prodev.ecomarket.donations.domain.model.queries.GetCompanyByIdQuery;
import com.prodev.ecomarket.donations.domain.model.queries.GetCompanyByNameQuery;

import java.util.List;
import java.util.Optional;

public interface CompanyQueryService {
    List<Company> handle(GetAllCompanysQuery query);
    Optional<Company> handle(GetCompanyByNameQuery query);
    Optional<Company> handle(GetCompanyByIdQuery query);
}
