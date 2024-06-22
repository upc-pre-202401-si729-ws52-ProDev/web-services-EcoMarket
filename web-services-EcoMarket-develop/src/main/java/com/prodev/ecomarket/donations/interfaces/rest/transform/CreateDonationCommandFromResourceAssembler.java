package com.prodev.ecomarket.donations.interfaces.rest.transform;

import com.prodev.ecomarket.donations.domain.model.commands.CreateDonationCommand;
import com.prodev.ecomarket.donations.infrastructure.persistence.jpa.repositories.ProductRepository;
import com.prodev.ecomarket.donations.interfaces.rest.resources.CreateDonationResource;
import com.prodev.ecomarket.donations.infrastructure.persistence.jpa.repositories.CompanyRepository;

public class CreateDonationCommandFromResourceAssembler {

    public static CreateDonationCommand toCommandFromResource(CreateDonationResource resource, ProductRepository productRepository, CompanyRepository companyRepository) {
        Long productId = productRepository.findByName(resource.product()).get().getId();
        Long companyId = companyRepository.findByName(resource.company()).get().getId();

        return new CreateDonationCommand(
                resource.quantity(),
                resource.description(),
                resource.ong(),
                productId,
                companyId);
    }
}