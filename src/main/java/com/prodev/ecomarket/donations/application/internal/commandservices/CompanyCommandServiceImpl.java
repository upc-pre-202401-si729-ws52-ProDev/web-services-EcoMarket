package com.prodev.ecomarket.donations.application.internal.commandservices;

import com.prodev.ecomarket.donations.domain.model.commands.CreateCompanyCommand;
import com.prodev.ecomarket.donations.domain.model.commands.UpdateCompanyCommand;
import com.prodev.ecomarket.donations.domain.model.entities.Company;
import com.prodev.ecomarket.donations.domain.services.CompanyCommandService;
import com.prodev.ecomarket.donations.infrastructure.persistence.jpa.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyCommandServiceImpl implements CompanyCommandService {
    private final CompanyRepository companyRepository;

    public CompanyCommandServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Optional<Company> handle(CreateCompanyCommand command) {
        var company = new Company(command);
        try {
            companyRepository.save(company);
            return Optional.of(company);
        } catch (Exception e) {
            throw new RuntimeException("Error creating company");
        }
    }

    @Override
    public Optional<Company> handle(UpdateCompanyCommand command) {
        var company = companyRepository.findById(command.id());
        if (company.isPresent()) {
            Company existingCompany = company.get();
            existingCompany.setRuc(command.ruc());
            existingCompany.setAboutDescription(command.aboutDescription());
            try {
                companyRepository.save(existingCompany);
                return Optional.of(existingCompany);
            } catch (Exception e) {
                throw new IllegalArgumentException("Error updating company", e);
            }
        } else {
            return Optional.empty();
        }
    }
}
