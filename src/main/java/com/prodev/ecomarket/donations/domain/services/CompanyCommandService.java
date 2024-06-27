package com.prodev.ecomarket.donations.domain.services;

import com.prodev.ecomarket.donations.domain.model.commands.CreateCompanyCommand;
import com.prodev.ecomarket.donations.domain.model.commands.UpdateCompanyCommand;
import com.prodev.ecomarket.donations.domain.model.entities.Company;

import java.util.Optional;

public interface CompanyCommandService {
    Optional<Company> handle(CreateCompanyCommand command);
    Optional<Company> handle(UpdateCompanyCommand command);
}
