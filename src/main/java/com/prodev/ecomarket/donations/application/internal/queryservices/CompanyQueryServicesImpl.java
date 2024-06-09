package com.prodev.ecomarket.donations.application.internal.queryservices;

import com.prodev.ecomarket.donations.domain.model.entities.Company;
import com.prodev.ecomarket.donations.domain.model.queries.GetCompanyByNameQuery;
import com.prodev.ecomarket.donations.domain.services.CompanyQueryService;
import com.prodev.ecomarket.donations.infrastructure.persistence.jpa.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyQueryServicesImpl implements CompanyQueryService {
    private final CompanyRepository companyRepository;

    public CompanyQueryServicesImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @Override
    public Optional<Company> handle(GetCompanyByNameQuery query) {
        return companyRepository.findByName(query.name());
    }
}
